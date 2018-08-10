package cn.fves24.id.controller.api;

import cn.fves24.id.db.service.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fves
 * @Date: ${Date} ${Time}
 **/
@RestController
@RequestMapping("/test")
public class TestAPI {
    private SummonerService summonerService;

    @Autowired
    public TestAPI(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
