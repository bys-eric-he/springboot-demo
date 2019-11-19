package com.example;

/**
 * API响应实体重写
 */
public class ResponseBase {
    private String code;
    private String msg;
    private Object data;

    public static ResponseBase error(String code, String msg) {
        ResponseBase response = new ResponseBase();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static ResponseBase ok(Object data) {
        ResponseBase response = new ResponseBase();
        response.setCode("200");
        response.setMsg("操作成功");
        response.setData(data);
        return response;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
