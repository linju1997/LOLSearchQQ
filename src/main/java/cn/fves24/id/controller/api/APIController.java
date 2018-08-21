package cn.fves24.id.controller.api;


import cn.fves24.id.auth.AuthHeader;
import cn.fves24.id.db.service.AccessCodeService;
import cn.fves24.id.db.service.SummonerService;
import cn.fves24.id.entity.dto.APIMessage;
import cn.fves24.id.entity.model.AccessCode;
import cn.fves24.id.entity.model.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author fves
 */
@RestController
@RequestMapping("/api")
public class APIController {
    private AccessCodeService accessCodeService;
    private SummonerService summonerService;

    @Autowired
    public APIController(AccessCodeService accessCodeService, SummonerService summonerService) {
        this.accessCodeService = accessCodeService;
        this.summonerService = summonerService;
    }

    @AuthHeader
    @PostMapping("/admin/code/add")
    public APIMessage saveCode(@Valid AccessCode accessCode, BindingResult result) {
        if (result.hasErrors()) {
            return new APIMessage(201, null, result.getFieldError().getDefaultMessage());
        }
        accessCode.setCode(accessCode.getCode().trim());
        accessCode.setModify(new Date());
        boolean saveAccessCode = accessCodeService.add(accessCode);
        if (saveAccessCode) {
            return new APIMessage(200, accessCode, "添加新的查询码成功!");
        }
        return new APIMessage(202, accessCode, "添加新的查询码失败,查询码可能存在!");
    }

    @AuthHeader
    @PostMapping("/admin/code/addtimes")
    public APIMessage addCode(@Valid AccessCode accessCode, BindingResult result) {
        if (result.hasErrors()) {
            return new APIMessage(201, null, result.getFieldError().getDefaultMessage());
        }
        accessCode.setCode(accessCode.getCode().trim());
        boolean add = accessCodeService.addTimes(accessCode.getCode(),accessCode.getTimes());
        if (add) {
            return new APIMessage(200, accessCode, "增加次数成功!");
        }
        return new APIMessage(202, accessCode, "增加次数失败!");
    }

    @AuthHeader
    @PostMapping("/admin/code/update")
    public APIMessage updateCode(@Valid AccessCode accessCode, BindingResult result) {
        if (result.hasErrors()) {
            return new APIMessage(201, null, result.getFieldError().getDefaultMessage());
        }
        accessCode.setCode(accessCode.getCode().trim());
        accessCode.setNote(accessCode.getNote().trim());
        accessCode.setModify(new Date());
        boolean update = accessCodeService.update(accessCode);
        if (update) {
            return new APIMessage(200, accessCode, "修改成功!");
        }
        return new APIMessage(202, accessCode, "修改失败!");
    }

    @AuthHeader
    @PostMapping("/admin/code/delete")
    public APIMessage deleteCode(@Valid AccessCode accessCode, BindingResult result) {
        if (result.hasErrors()) {
            return new APIMessage(201, null, result.getFieldError().getDefaultMessage());
        }
        accessCode.setCode(accessCode.getCode().trim());
        boolean delete = accessCodeService.delete(accessCode.getCode());
        if (delete) {
            return new APIMessage(200, accessCode, "删除成功");
        }
        return new APIMessage(202, accessCode, "删除失败");
    }

    @AuthHeader
    @PostMapping("/admin/query")
    public APIMessage query() {
        List<AccessCode> allTimes = accessCodeService.findAccessCodes();
        return new APIMessage(200, allTimes, "获取成功");
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
