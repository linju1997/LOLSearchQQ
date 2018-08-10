package cn.fves24.id.db.service;

import cn.fves24.id.db.mapper.SummonerMapper;
import cn.fves24.id.entity.model.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fves
 * @Date: ${Date} ${Time}
 **/
@Service
public class SummonerService {
    private SummonerMapper summonerMapper;

    @Autowired
    public SummonerService(SummonerMapper summonerMapper) {
        this.summonerMapper = summonerMapper;
    }

    public boolean saveSummoner(Summoner summoner,String code) {
        Integer integer = summonerMapper.saveSummoner(summoner, code);
        return integer == 1;
    }

    public List<Summoner> searchSumonerByCode(String code) {
        return summonerMapper.searchSummoner(code);
    }

}
