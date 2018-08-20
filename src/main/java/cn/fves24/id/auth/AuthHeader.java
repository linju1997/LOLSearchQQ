package cn.fves24.id.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释在 需要Header，Auth 验证的 请求方法上
 * @Author: fves
 * @Date: 2018-8-17 22:33
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthHeader {
}
