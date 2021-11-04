package com.example.common.constant;

/**
 * 接口返回参数枚举
 *
 * @author yangsen
 * @date 2021/7/28 15:28
 */
public enum ApiStateEnum {
    /**
     * 全局的响应状态枚举
     */
    API_GLOBAL_S(Constant.SUCCESS, "响应成功"),
    API_GLOBAL_F(Constant.FAIL, "服务器内部错误，请联系管理员"),
    API_GLOBAL_TIP(Constant.AUTH_FAIL, "鉴权失败"),
    API_GLOBAL_PARAM_ERROR(Constant.PARAM_ERROR, "参数错误"),
    API_GLOBAL_NODATA(Constant.SUCCESS, "暂无数据"),
    ;

    private int code;
    private String msg;

    ApiStateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static String getMsg(int code) {
        for (ApiStateEnum stateEnum : values()) {
            if (stateEnum.getCode() == code) {
                return stateEnum.getMsg();
            }
        }
        return null;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String msg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
