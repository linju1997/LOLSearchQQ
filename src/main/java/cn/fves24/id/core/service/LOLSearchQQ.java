package cn.fves24.id.core.service;

import cn.fves24.id.entity.model.Summoner;
import cn.fves24.id.util.LOLUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据GameID和AreaID 获取QQ号码
 */
public class LOLSearchQQ {
    /**
     * 获取 名称为summoner.name的QQ号码
     * @param summoner summoner
     * @return 获取到的QQ号码
     */
    public static String getQQNumber(Summoner summoner) {
        String url = "http://apps.game.qq.com/lol/lolapi/BattleInfo.php?a0=combatGains&areaId=" + summoner.getAreaId() + "&gameId=";
        String game = url + summoner.getGameId();
        String json = LOLUtil.get(game);
        if (json == null) {
            return null;
        }
        JSONObject ret = JSONObject.parseObject(json);
        JSONArray participants = ret.getJSONArray("participants");
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            JSONObject participant = participants.getJSONObject(i);
            String summonerName = participant.getString("summonerName");
            if (summoner.getName().equalsIgnoreCase(summonerName)) {
                String qq = participant.getString("partnerId");
                summoner.setQq(qq);
                return qq;
            }
        }
        return null;
    }

    /**
     * 获取该场游戏所有人的QQ号码
     * @param summoner summoner
     * @return 召唤师列表
     */
    public static List<Summoner> getAllQQNumber(Summoner summoner) {
        String url = "http://apps.game.qq.com/lol/lolapi/BattleInfo.php?a0=combatGains&areaId=" + summoner.getAreaId() + "&gameId=";
        String game = url + summoner.getGameId();
        String json = LOLUtil.get(game);

        if (json == null) {
            return null;
        }
        JSONArray participants = JSONObject.parseObject(json).getJSONArray("participants");
        List<Summoner> list = new ArrayList<>();
        for (Object participant : participants) {
            Summoner tem = new Summoner();
            JSONObject para = (JSONObject) participant;
            tem.setAreaId(summoner.getAreaId());
            tem.setName(para.getString("summonerName"));
            tem.setQq(para.getString("partnerId"));
            list.add(tem);
        }
        return list;
    }
}
