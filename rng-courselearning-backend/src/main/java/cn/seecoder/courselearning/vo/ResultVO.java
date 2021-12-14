package cn.seecoder.courselearning.vo;

import lombok.Data;
import lombok.NonNull;

@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultVO(){

    }

    public ResultVO(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg, @NonNull T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    //for test
    public Integer getCode(){return code;}
    public String getMsg(){return msg;}
    public T getData(){return data;}
}
