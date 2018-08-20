package cn.fves24.id.core;

import cn.fves24.id.core.service.LOLSearchQQ;
import cn.fves24.id.entity.model.Summoner;
import cn.fves24.id.util.DuoWanArea;
import cn.fves24.id.util.LOLUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 采用多玩
 */
@Service
public class DuoWanLOLService {

    private Long getUserId(Summoner summoner) {
        String url = "http://api.lolbox.duowan.com/api/v3/player/search/?";
        url = url + "game_zone=" + LOLUtil.encodeParam(summoner.getAreaName()) + "&player_name_list=" + LOLUtil.encodeParam(summoner.getName()) + "&callback=jQuery";
        String res = LOLUtil.get(url);
        if (res == null) {
            return null;
        }
        String json = res.substring(7, res.length() - 1);
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray playerList = jsonObject.getJSONArray("player_list");
        if (playerList.size() == 0) {
            return null;
        }
        return playerList.getJSONObject(0).getLong("user_id");
    }

    private String getGameID(Summoner summoner) {
        String url = "http://apps.game.qq.com/lol/lolapi/BattleInfo.php?a0=matchList&areaId=" + summoner.getAreaId() + "&accountId=" + summoner.getLolId();
        String json = LOLUtil.get(url);
        JSONObject ret = JSONObject.parseObject(json);
        if (0 != ret.getInteger("status")) {
            return null;
        }
        JSONObject msg = ret.getJSONObject("msg");
        JSONArray games = msg.getJSONArray("games");
        int startIndex = msg.getInteger("startIndex");
        JSONObject first = games.getJSONObject(startIndex);
        return first.getString("gameId");
    }

    public String searchQQNumber(Summoner summoner) {
        summoner.setAreaName(DuoWanArea.getGameZoneByid(summoner.getAreaId()));
        DuoWanLOLService duoWanLolService = new DuoWanLOLService();
        Long userId = duoWanLolService.getUserId(summoner);
        if (userId == null) {
            return null;
        }
        summoner.setLolId(userId);
        String gameID = duoWanLolService.getGameID(summoner);
        if (gameID == null) {
            return null;
        }
        summoner.setGameId(gameID);
        String qq = LOLSearchQQ.getQQNumber(summoner);
        if (qq == null) {
            return null;
        }
        return qq;
    }
}
