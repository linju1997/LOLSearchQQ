package cn.fves24.id.controller.page;

import cn.fves24.id.area.Area;
import cn.fves24.id.db.service.AccessCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 页面路由
 * @author Administrator
 */
@Controller
public class LOLPageController {
    private List<Area> areaList = new ArrayList<>(Arrays.asList(Area.values()));
    private AccessCodeService accessCodeService;

    @Autowired
    public LOLPageController(AccessCodeService accessCodeService) {
        this.accessCodeService = accessCodeService;
    }

    /**
     * @param model model
     * @return 返回主页
     */
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute(areaList);
        return "index";
    }
    @GetMapping("/times")
    public String getTimes() {
        return "times";
    }

    /**
     * @return 返回菜单页
     */
    @GetMapping("/admin")
    public String admin() {
        return "redirect:/admin/";
    }

    @GetMapping("/custom/{code}")
    public String custom(@PathVariable("code") String code, Model model){
        Integer timesByCode = accessCodeService.remainTimes(code);
        model.addAttribute("times", timesByCode);
        model.addAttribute("areaList", areaList);
        model.addAttribute("code", code);
        return "custom";
    }

    @GetMapping("/history")
    public String history(){
        return "history";
    }

    @GetMapping("/allsummoner")
    public String allSummoner(Model model) {
        model.addAttribute("areaList", areaList);
        return "allsummoner";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
