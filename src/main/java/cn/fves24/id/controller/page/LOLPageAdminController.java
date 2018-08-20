package cn.fves24.id.controller.page;

import cn.fves24.id.auth.AuthHeader;
import cn.fves24.id.util.WebConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/admin")
public class LOLPageAdminController {
    /**
     * @return 返回主页
     */
    @GetMapping("/")
    public String getIndex() {
        return "admin/vali";
    }

    /**
     * @return 返回菜单页面
     */
    @AuthHeader
    @GetMapping("/index")
    public String setMenu() {
        return "admin/index";
    }

    /**
     * @return 返回查询码操作页面
     */
    @AuthHeader
    @GetMapping("/code")
    public String setCode() {
        return "admin/code";
    }

    /**
     * @return 批量生成查询码
     */
    @AuthHeader
    @GetMapping("/codes")
    public String setCodes(){
        return "admin/codes";
    }
}
