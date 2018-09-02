package cn.fves24.id.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: fves
 * @Date: 2018-8-18 2:41
 **/
@Controller
public class LoginController {

    @Value("${password}")
    private String password;


    @PostMapping("/login")
    public void login(String auth,HttpServletResponse response) throws IOException {
        if (password.equals(auth)) {
            response.addCookie(new Cookie("auth", password));
            response.sendRedirect("admin/menu");
        } else {
            response.sendRedirect("admin/");
        }
    }
}
