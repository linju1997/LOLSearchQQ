package cn.fves24.id.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 查询码
 *
 * @author Administrator
 */
@Data
public class AccessCode {
    private Integer id;
    /**
     * 查询码
     */
    @NotBlank(message = "查询码不能为空")
    private String code;
    /**
     * 次数
     */
    private int times = 0;
    /**
     * 备注
     */
    private String note;
    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modify;

    public AccessCode() {}

    public AccessCode(String code, int times, String note, Date modify) {
        this.code = code;
        this.times = times;
        this.note = note;
        this.modify = modify;
    }
}
