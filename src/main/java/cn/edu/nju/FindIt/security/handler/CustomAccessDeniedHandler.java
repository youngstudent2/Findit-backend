package cn.edu.nju.FindIt.security.handler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import cn.edu.nju.FindIt.utils.enums.ExceptionEnum;
import cn.edu.nju.FindIt.utils.exception.BasicException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * 访问接口无权限时的处理端点, 一般是抛出AccessDeniedException异常时会进入
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AccessDeniedException e) throws IOException, ServletException {
        // 删除框架抛出的异常，并通知客户端
        httpServletRequest.removeAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR");
        HashMap<String, String> map = new HashMap<>(2);
        map.put("uri", httpServletRequest.getRequestURI());
        map.put("msg", "无权限！");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = objectMapper.writeValueAsString(map);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}