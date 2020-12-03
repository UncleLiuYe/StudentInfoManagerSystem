package site.liuye.studentinfomanager.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/12/1 15:59
 */
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Object principal = authentication.getPrincipal();
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", principal);
        printWriter.println(new ObjectMapper().writeValueAsString(map));
        printWriter.flush();
        printWriter.close();
    }
}
