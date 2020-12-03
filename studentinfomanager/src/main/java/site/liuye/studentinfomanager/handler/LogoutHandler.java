package site.liuye.studentinfomanager.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/12/1 16:00
 */
public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "退出成功");
        printWriter.println(new ObjectMapper().writeValueAsString(map));
        printWriter.flush();
        printWriter.close();
    }
}
