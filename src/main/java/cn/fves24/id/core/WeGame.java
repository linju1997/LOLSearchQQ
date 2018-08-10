package cn.fves24.id.core;

import cn.fves24.id.entity.model.Summoner;
import cn.fves24.id.util.LOLUtil;
import cn.fves24.id.util.WeGameHttpUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: fves
 **/
public class WeGame {
    private Summoner summoner;

    public WeGame(Summoner summoner){
        this.summoner = summoner;
    }
    /**
     * 获取 到 用户的lolid,qquin
     * @return 召唤师
     */
    public Summoner searchSummoner() {
        String url = "https://api.pallas.tgp.qq.com/core/search_player?callback=jQuery&key=" + WeGameHttpUtils.encodeParam(summoner.getName());
        String ret = WeGameHttpUtils.get(url);
        // 没有该角色
        if ("{}".equals(ret) || ret == null) {
            return null;
        }
        JSONArray jsonArray = JSONArray.parseArray(ret);
        for (Object o : jsonArray) {
            JSONObject data = (JSONObject) o;
            Integer tAreaId = data.getInteger("area_id");
            if (tAreaId.equals(summoner.getAreaId())) {
                summoner.setQquin(data.getString("qquin"));
                return summoner;
            }
        }
        return null;
    }

    /**
     * 获取 lolid
     */
    public void searchLOLId(){
        String param = "[[2,{\"area_id\":\""+summoner.getAreaId()+"\",\"qquin\":\""+summoner.getQquin()+"\"}]]";
        String url = "https://api.pallas.tgp.qq.com/core/tcall?callback=jQuery&p=" + WeGameHttpUtils.encodeParam(param);
        String ret = WeGameHttpUtils.get(url);
        JSONArray jsonObject = JSONObject.parseArray(ret);
        assert jsonObject != null;
        JSONObject basicInfo = jsonObject.getJSONObject(0).getJSONObject("basic_info");
        summoner.setLolId(basicInfo.getLong("lolid"));
    }
    /**
     * 通过QQuin和AreaID获取 GameID
     * @return 是否获取到GameID
     */
    public boolean searchGameId1() {
        String base = "https://api.pallas.tgp.qq.com/core/tcall?callback=jQuery&p=";
        String url = base + WeGameHttpUtils.encodeParam("[[3,{\"qquin\":\"" + summoner.getQquin() + "\",\"area_id\":\"" + summoner.getAreaId() + "\"}]]");
        String ret = WeGameHttpUtils.get(url);
        if (ret == null) {
            return false;
        }
        JSONObject jsonObject = JSONObject.parseArray(ret).getJSONObject(0);
        if (jsonObject.getInteger("result") != 0 || jsonObject.getInteger("total_num") == 0) {
            return false;
        }
        String gameId = jsonObject.getJSONArray("battle_list").getJSONObject(0).getString("game_id");
        summoner.setGameId(gameId);
        return true;
    }

    /**
     * 通过LOLID和AreaID 获取GameID
     * @return 是否获取到GameID
     */
    public boolean searchGameId2(){
        String ret = LOLUtil.get("http://apps.game.qq.com/lol/lolapi/BattleInfo.php?a0=matchList&areaId="+summoner.getAreaId()+"&accountId="+summoner.getLolId());
        if (ret == null) {
            return false;
        }
        String gameId = JSONObject.parseObject(ret).getJSONArray("game").getJSONObject(0).getString("gameId");
        summoner.setGameId(gameId);
        return false;
    }
}
