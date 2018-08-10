package cn.fves24.id.db.mapper;

import cn.fves24.id.entity.model.Summoner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fves
 * @Date: ${Date} ${Time}
 **/
@Mapper
@Repository
public interface SummonerMapper {
    /**
     * 保存召唤师
     * @param summoner 召唤师
     * @param code 查询码
     * @return 保存结果
     */
    Integer saveSummoner(@Param("summoner") Summoner summoner,@Param("code") String code);

    /**
     * 通过查询码 查询码召唤师
     * @param code 查询码
     * @return 查询结果
     */
    List<Summoner> searchSummoner(String code);
}
