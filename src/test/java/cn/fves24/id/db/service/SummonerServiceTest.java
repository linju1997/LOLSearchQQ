package cn.fves24.id.db.service;

import cn.fves24.id.entity.model.Summoner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: fves
 * @Date: 2018-8-7 20:02
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SummonerServiceTest {
    @Autowired
    private SummonerService summonerService;

    @Test
    public void saveSummoner() {
        Summoner summoner = new Summoner();
        summoner.setName("zhangsan");
        summoner.setAreaName("艾欧尼亚");
        summoner.setQq("123123123");

        String code = "test";

        System.out.println(summonerService.saveSummoner(summoner,code));
    }

    @Test
    public void searchSumonerByCode() {
        String code = "test";
        List<Summoner> summoners = summonerService.searchSumonerByCode(code);
        System.out.println(summoners);
    }
}