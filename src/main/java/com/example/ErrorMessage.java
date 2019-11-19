package com.example;

public class ErrorMessage {
    private String code;
    private String msg;
    private Integer hasException;

    public ErrorMessage(String code, String msg, Integer hasException) {
        this.code = code;
        this.msg = msg;
        this.hasException = hasException;
    }

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

    public Integer getHasException() {
        return hasException;
    }

    public void setHasException(Integer hasException) {
        this.hasException = hasException;
    }
}
