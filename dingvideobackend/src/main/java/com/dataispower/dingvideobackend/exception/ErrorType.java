package com.dataispower.dingvideobackend.exception;

public enum ErrorType {
    WRONG_USERNAME_PASSWORD("4000001", "用户名或密码错误"),
    UNKNOWN_USERNAME("4000002", "找不到用户"),
    USERNAME_EXISTS("4000003", "用户名已存在");

    ErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
