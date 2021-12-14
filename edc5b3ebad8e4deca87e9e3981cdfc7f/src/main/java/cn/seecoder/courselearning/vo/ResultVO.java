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

    public void setMsg(String s) {
        this.msg=s;
    }

    public void setCode(Integer requestCondition) {
        this.code=requestCondition;
    }

    public void setData(T userVO) {
        this.data=userVO;
    }
}
