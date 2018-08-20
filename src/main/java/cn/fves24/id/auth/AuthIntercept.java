package cn.fves24.id.auth;

import cn.fves24.id.entity.dto.APIMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.val;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限验证 拦截器
 * @Author: fves
 * @Date: 2018-8-17 22:40
 **/
@Component
public class AuthIntercept implements HandlerInterceptor {
    @Value("${password}")
    private String password;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /*
          以UTF-8 编写 往响应中写数据
         */
        response.setCharacterEncoding("UTF-8");


        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        /*
          如果 请求方法 使用AuthHeader 则该请求 需要认证
          否则，直接放行
         */

        if (!method.hasMethodAnnotation(AuthHeader.class)) {
            return true;
        }

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("auth".equals(cookie.getName())) {
                if (password.equals(cookie.getValue())) {
                    return true;
                }
            }
        }
        try (PrintWriter writer = response.getWriter()) {
            APIMessage apiMessage = new APIMessage(HttpStatus.SC_UNAUTHORIZED, "", "权限拒绝");
            writer.write(JSONObject.toJSONString(apiMessage));
        } catch (IOException ignore) {}
        return false;
    }
}
