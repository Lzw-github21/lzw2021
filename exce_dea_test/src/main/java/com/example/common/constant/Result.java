package com.example.common.constant;

import java.io.Serializable;

/**
 * 响应数据
 *
 * @author yangsen
 * @date 2021/7/28 15:28
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    private int code = 200;
    /**
     * 消息内容
     */
    private String msg = "success";
    /**
     * 响应数据
     */
    private T data;

    public Result<T> ok() {
        return this;
    }

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success(){
        return code == 200;
    }

    public Result<T> error() {
        this.code = ApiStateEnum.API_GLOBAL_F.getCode();
        this.msg = ApiStateEnum.API_GLOBAL_F.getMsg();
        return this;
    }

    public Result<T> error(int code) {
        this.code = code;
        this.msg = ApiStateEnum.getMsg(this.code);
        return this;
    }

    public Result<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<T> error(ApiStateEnum apiStateEnum) {
        this.code = apiStateEnum.code();
        this.msg = apiStateEnum.msg();
        return this;
    }

    public Result<T> error(String msg) {
        this.code = ApiStateEnum.API_GLOBAL_F.code();
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
