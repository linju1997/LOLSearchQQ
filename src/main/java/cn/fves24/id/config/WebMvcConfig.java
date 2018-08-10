package cn.fves24.id.config;

import cn.fves24.id.intercept.AdminIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private final AdminIntercept adminIntercept;

    @Autowired
    public WebMvcConfig(AdminIntercept adminIntercept) {
        this.adminIntercept = adminIntercept;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminIntercept)
                .addPathPatterns("/admin/index")
                .addPathPatterns("/admin/code")
                .addPathPatterns("/api/admin/**");
    }
}
