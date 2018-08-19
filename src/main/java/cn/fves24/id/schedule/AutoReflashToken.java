package cn.fves24.id.schedule;

import cn.fves24.id.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时刷新Token
 *
 * @Author: fves
 * @Date: 2018-8-19 15:25
 **/
@Component
@Slf4j
public class AutoReflashToken {

    /**
     * 定时三小时 刷新一次token
     */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 3)
    public void reflushToken() {
        boolean writeCookie = CookieUtil.writeCookie();
        log.info(LocalDateTime.now() + " 刷新Token，刷新状态：" + writeCookie);
    }
}
