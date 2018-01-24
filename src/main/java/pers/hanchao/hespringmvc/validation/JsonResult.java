package pers.hanchao.hespringmvc.validation;

/**
 * <p>返回Json的实体</p></p>
 * @author hanchao 2018/1/20 14:34
 **/
public class JsonResult {
    private String code;
    private String message;

    public JsonResult() {
        this.code = "1";
        this.message = "success!";
    }

    public JsonResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCodeAndMessage(String code, String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public String toString() {
        return "NewsJsonResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
