package cn.fves24.id.controller.api;


import cn.fves24.id.db.service.SummonerService;
import cn.fves24.id.entity.dto.APIMessage;
import cn.fves24.id.db.service.AccessCodeService;
import cn.fves24.id.entity.model.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class LOLRestController {

    private AccessCodeService accessCodeService;
    private SummonerService summonerService;
    
    @Autowired
    public LOLRestController(AccessCodeService accessCodeService, SummonerService summonerService) {
        this.accessCodeService = accessCodeService;
        this.summonerService = summonerService;
    }

    @PostMapping("/times")
    public APIMessage getTimes(String code) {
        boolean exists = accessCodeService.existsByCode(code);
        if (!exists) {
            return new APIMessage(201, null, "查询码不存在");
        }
        int times = accessCodeService.remainTimes(code);
        return new APIMessage(200, times, "获取成功");
    }

    @PostMapping("/searchbycode")
    public APIMessage getSummonerByCode(String code) {
        boolean exists = accessCodeService.existsByCode(code);
        if (!exists) {
            return new APIMessage(201, null, "查询码不存在");
        }
        List<Summoner> summoners = summonerService.searchSumonerByCode(code);
        return new APIMessage(200, summoners, null);
    }
}
