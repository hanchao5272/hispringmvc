package pers.hanchao.hespringmvc.restful;

import java.util.Arrays;
import java.util.List;

/**
 * <p>用来封装返回值</p>
 * @author hanchao 2018/1/21 11:29
 **/
public class MyJsonResult {
    /** 状态码 */
    private String code = "1";
    /** 状态消息 */
    private String message = "success!";
    /** 返回消息 */
    private Question[] questions;

    public MyJsonResult(){
    }

    public MyJsonResult(Question[] data){
        this.questions = data;
    }
    public void setCodeAndMessage(String code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "NewsJsonResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", questions=" + Arrays.toString(questions) +
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

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
