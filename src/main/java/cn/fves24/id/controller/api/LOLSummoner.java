package cn.fves24.id.controller.api;

import cn.fves24.id.area.Area;
import cn.fves24.id.core.WeGame;
import cn.fves24.id.core.service.LOLSearchQQ;
import cn.fves24.id.db.service.SummonerService;
import cn.fves24.id.entity.dto.APIMessage;
import cn.fves24.id.entity.model.Summoner;
import cn.fves24.id.db.service.AccessCodeService;
import cn.fves24.id.core.DuoWanLOLService;
import cn.fves24.id.util.record.QueryRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LOLSummoner {
    private DuoWanLOLService duoWanLOLService;
    private AccessCodeService accessCodeService;
    private SummonerService summonerService;

    @Autowired
    public LOLSummoner(DuoWanLOLService duoWanLOLService, AccessCodeService accessCodeService, SummonerService summonerService) {
        this.duoWanLOLService = duoWanLOLService;
        this.accessCodeService = accessCodeService;
        this.summonerService = summonerService;
    }

    /**
     * 查询召唤师QQ   采用多玩  接口
     *
     * @param code 查询码
     * @return 查询结果
     */
    @PostMapping("/summoner1")
    public APIMessage getSummoner(@Valid Summoner summoner, BindingResult result, String code) {
        if (result.hasErrors()) {
            return new APIMessage(201, result.getFieldError().getDefaultMessage(), null);
        }
        String qq = duoWanLOLService.searchQQNumber(summoner);
        if (qq == null) {
            return new APIMessage(201, null, "可能查询不到，请稍后重试");
        }
        boolean b = accessCodeService.existsByCode(code);
        if (b) {
            boolean reduce = accessCodeService.reduceTimes(code);
            if (reduce) {
                return new APIMessage(200, qq, "查询成功");
            }
        }
        int lan = qq.length();
        StringBuilder builder = new StringBuilder();
        builder.append(qq.charAt(0));
        for (int i = 0; i < lan - 1; i++) {
            builder.append("*");
        }
        builder.append(qq.charAt(lan - 1));
        return new APIMessage(200, builder.toString(), "查询成功");
    }

    /**
     * 英雄联盟查 QQ  采用 WeGame 查询接口
     *
     * @param code 查询码
     */
    @PostMapping("/summoner")
    public APIMessage weGameSummoner(@Valid Summoner summoner, BindingResult result, String code) {
        if (result.hasErrors()) {
            return new APIMessage(201, result.getFieldError().getDefaultMessage(), null);
        }
        summoner.setName(summoner.getName().trim());
        summoner.setAreaName(Area.getName(summoner.getAreaId()));
        /* 查询记录 */
        LocalDateTime time = LocalDateTime.now();
        String timeStr = time.getMonthValue() + "/" + time.getDayOfMonth() + " " + time.getHour() + ":" + time.getMinute();
        String tt = timeStr + "|" + summoner.getName() + "|" + summoner.getAreaName() + "|" + code+"|";
        /* END */

        code = code.trim();
        WeGame weGame = new WeGame(summoner);
        summoner = weGame.searchSummoner();
        if (summoner == null) {
            return new APIMessage(202, null, "没有查询到该角色,请检查ID和大区");
        }
        boolean havaGameId = weGame.searchGameId1();
        weGame.searchLOLId();
        if (!havaGameId) {
            havaGameId = weGame.searchGameId2();
            if (!havaGameId) {
                /* 查询记录 */
                tt += " 无战绩";
                QueryRecord.writeToFile(tt);
                /* END */
                return new APIMessage(202, null, "暂时查不到，请稍后重试");
            }
        }
        String qq = LOLSearchQQ.getQQNumber(summoner);
        if (qq == null) {
            /* 查询记录 */
            tt += "暂时查不到，请稍后重试";
            QueryRecord.writeToFile(tt);
            /* END */
            return new APIMessage(203, null, "暂时查不到，请稍后重试");
        }
        /*
           到此步骤，已经查到QQ，  根据查询码 查询码
         */
        String maskQ = maskQQ(qq);
        boolean exists = accessCodeService.existsByCode(code);
        /* 查询记录 */
        tt += qq;
        QueryRecord.writeToFile(tt);
        /* END */
        if (!exists) {
            if ("".equals(code)) {
                return new APIMessage(200, maskQ, "恭喜,可以查到QQ,购买查询码即可查询完整QQ");
            } else {
                return new APIMessage(200, maskQ, "查询码不存在,请购买正确的查询码进行查询");
            }
        }
        /*
        减少次数
         */
        boolean reduceTimes = accessCodeService.reduceTimes(code);
        if (!reduceTimes) {
            return new APIMessage(204, maskQ, "次数已用完,如需查询记录,请点击下面查询记录按钮");
        }
        summonerService.saveSummoner(summoner, code);
        return new APIMessage(200, qq, "查询成功");
    }

    @PostMapping("/allsummoner")
    public APIMessage weGameAllSummoner(@Valid Summoner summoner, BindingResult result) {
        if (result.hasErrors()) {
            return new APIMessage(201, result.getFieldError().getDefaultMessage(), null);
        }
        /*
         参数去前后空白处理
         */
        summoner.setName(summoner.getName().trim());
        WeGame weGame = new WeGame(summoner);
        summoner = weGame.searchSummoner();
        if (summoner == null) {
            return new APIMessage(201, null, "没有查询到该角色,请检查ID和大区");
        }

        boolean havaGameId = weGame.searchGameId1();
        if (!havaGameId) {
            havaGameId = weGame.searchGameId2();
            if (!havaGameId) {
                return new APIMessage(202, null, "无战绩的ID,暂不支持查询");
            }
        }
        List<Summoner> summoners = LOLSearchQQ.getAllQQNumber(summoner);
        if (summoners == null) {
            return new APIMessage(201, summoners, "暂时查不到");
        }

        return new APIMessage(200, summoners, "查询成功");
    }

    public String maskQQ(String str) {
        StringBuilder maskQ = new StringBuilder();
        int length = str.length();

        maskQ.append(str, 0, 2);
        for (int i = 0; i < length - 4; i++) {
            maskQ.append("*");
        }
        maskQ.append(str.substring(length-2));
        return maskQ.toString();
    }
}
