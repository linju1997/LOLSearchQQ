package cn.fves24.id.entity.dto;

import lombok.Data;

/**
 * Api 返回数据格式
 * @author Administrator
 */

@Data
public class APIMessage {
    private int code;
    private Object data;
    private String message;

    public APIMessage(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
