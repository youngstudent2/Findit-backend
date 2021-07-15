package cn.edu.nju.FindIt.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.edu.nju.FindIt.model.User;
import cn.edu.nju.FindIt.security.wx.WxAuthenticationToken;
import cn.edu.nju.FindIt.service.AuthService;
import cn.edu.nju.FindIt.utils.JwtTokenUtils;
import cn.edu.nju.FindIt.utils.enums.ConstantEnum;
import cn.edu.nju.FindIt.utils.enums.ExceptionEnum;
import cn.edu.nju.FindIt.utils.exception.BasicException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

/**
 * 用来校验请求头中的jwt是否有效，以此为依据来认证用户是否登录
 * @author tanwubo
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private void solveUnauthorized(HttpServletRequest request, HttpServletResponse response, String msg){
        try{
            HashMap<String, String> map = new HashMap<>(2);
            map.put("uri", request.getRequestURI());
            map.put("msg", msg);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("utf-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ObjectMapper objectMapper = new ObjectMapper();
            String resBody = objectMapper.writeValueAsString(map);
            PrintWriter printWriter = response.getWriter();
            printWriter.print(resBody);
            printWriter.flush();
            printWriter.close();
            return;
        }catch(Exception e){
            e.printStackTrace();;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("processing authentication for [{}]", request.getRequestURI());
        String token = request.getHeader(ConstantEnum.AUTHORIZATION.getValue());
        String NJUID = null;
        if (token != null) {
            try {
                NJUID = jwtTokenUtils.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                log.error("an error occurred during getting NJUID from token");
                solveUnauthorized(request, response, "获取token异常，请重新登陆！");
            } catch (ExpiredJwtException e) {
                log.warn("the token is expired and not valid anymore");
                solveUnauthorized(request, response, "未登陆！");
                return;
            }catch (SignatureException e) {
                log.warn("JWT signature does not match locally computed signature");
                solveUnauthorized(request, response, "权限验证异常，请重新登陆！");
                return;
            }
        }else {
            log.warn("couldn't find token string");
        }
        if (NJUID != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("security context was null, so authorizing user");
            User user = authService.findUserByNJUID(NJUID);
            List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            log.info("authorized user [{}], setting security context", NJUID);
            SecurityContextHolder.getContext().setAuthentication(new WxAuthenticationToken(NJUID, authorities));
        }
        filterChain.doFilter(request, response);
    }
}
