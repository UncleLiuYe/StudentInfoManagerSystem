package site.liuye.studentinfomanager.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/12/1 15:58
 */
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        Map<String, Object> map = new HashMap<>();
        if (e instanceof BadCredentialsException) {
            map.put("msg", "用户名或密码错误！");
        } else {
            map.put("msg", "登陆失败！");
        }
        printWriter.println(new ObjectMapper().writeValueAsString(map));
        printWriter.flush();
        printWriter.close();
    }
}
