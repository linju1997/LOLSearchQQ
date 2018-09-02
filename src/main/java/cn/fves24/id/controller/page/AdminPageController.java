package cn.fves24.id.controller.page;

import cn.fves24.id.auth.AuthHeader;
import cn.fves24.id.entity.model.Record;
import cn.fves24.id.util.record.QueryRecord;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Value("${password}")
    private String password;
    /**
     * @return 返回主页
     */
    @GetMapping("/")
    public String getIndex(@CookieValue(required = false) String auth) {
        if (password.equals(auth)) {
            return "admin/menu";
        }
        return "admin/vali";
    }

    /**
     *
     * @return 返回到菜单页面
     */
    @AuthHeader
    @GetMapping("/menu")
    public String getMenu(){
        return "admin/menu";
    }
    /**
     *
     * @return 返回查询code页面
     */
    @AuthHeader
    @GetMapping("/code")
    public String setCode() {
        return "admin/code";
    }

    /**
     *
     * @return 返回到通知页面
     */
    @AuthHeader
    @GetMapping("/notice")
    public String setNotice(){
        return "admin/notice";
    }

    /**
     * @return 返回查询码操作页面
     */
    @AuthHeader
    @GetMapping("/addcode")
    public String setAddCode() {
        return "admin/addcode";
    }

    /**
     * @return 批量生成查询码
     */
    @AuthHeader
    @GetMapping("/addcodes")
    public String setAddCodes(){
        return "admin/addcodes";
    }

    @AuthHeader
    @GetMapping("/record")
    public String record(Model model){
        List<Record> list = QueryRecord.readToList();
        Collections.reverse(list);
        model.addAttribute("list",list);
        return "admin/record";
    }

}
