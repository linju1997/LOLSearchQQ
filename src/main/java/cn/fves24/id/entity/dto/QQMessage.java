package cn.fves24.id.entity.dto;

/**
 * 封装查询QQ时的结果信息
 * @author Administrator
 */
public class QQMessage {
    private int code;
    private String message;
    private String qq;

    public QQMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public QQMessage(int code, String message, String qq) {
        this.code = code;
        this.message = message;
        this.setQq(qq);
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
