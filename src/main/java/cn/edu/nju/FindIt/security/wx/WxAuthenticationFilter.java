package cn.edu.nju.FindIt.security.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.client.RestTemplate;

import cn.edu.nju.FindIt.utils.DTO.WxLoginResultDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsrcret;

    private String wxAuthUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Autowired
    private RestTemplate restTemplate;

    public WxAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

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
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String code = httpServletRequest.getParameter("code");
        if(StringUtils.isBlank(code)){
            log.error("code is null");
            solveUnauthorized(httpServletRequest, httpServletResponse, "code 不能为空，请重新登陆！");
        }
        String NJUID = httpServletRequest.getParameter("NJUID");
        if(StringUtils.isBlank(NJUID)){
            log.error("NJUID is null");
            solveUnauthorized(httpServletRequest, httpServletResponse, "NJUID 不能为空，请重新登陆！");
        }
        String url = String.format(wxAuthUrl, appid, appsrcret, code);
        log.info("wx auth url: [{}]", url);
        WxLoginResultDTO wxLoginResult = restTemplate.getForObject(url, WxLoginResultDTO.class);
        if(wxLoginResult.getErrcode() != null && !wxLoginResult.getErrcode().equals(0)){
            log.error("wx auth failed, errCode is [{}], errMsg is [{}]", wxLoginResult.getErrcode(), wxLoginResult.getErrmsg());
            solveUnauthorized(httpServletRequest, httpServletResponse, "登陆异常：" + wxLoginResult.getErrmsg() + "， 请重新登陆");
        }
        log.info("wx login result: [{}]", wxLoginResult);
        return this.getAuthenticationManager().authenticate(new WxAuthenticationToken(wxLoginResult.getOpenid(), wxLoginResult.getSession_key(), NJUID, "signature"));
    }

}
