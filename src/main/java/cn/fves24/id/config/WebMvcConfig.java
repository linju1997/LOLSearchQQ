package cn.fves24.id.config;

import cn.fves24.id.auth.AuthIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private AuthIntercept authIntercept;

    @Autowired
    public WebMvcConfig(AuthIntercept authIntercept) {
        this.authIntercept = authIntercept;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authIntercept).addPathPatterns("/**");
    }
}
