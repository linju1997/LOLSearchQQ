package cn.fves24.id.intercept;

import cn.fves24.id.util.WebConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: fves
 * @Date: ${Date} ${Time}
 **/

@Component
public class AdminIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/admin/");
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("auth".equals(cookie.getName())) {
                if (WebConfig.AUTH.equals(cookie.getValue())) {
                    return true;
                }
                break;
            }
        }
        response.sendRedirect("/admin/");
        return false;
    }
}
