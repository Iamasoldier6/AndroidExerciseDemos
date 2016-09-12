package com.iamasoldier6.tulingrobotdemo.bean;

/**
 * @author：Iamasoldier6
 * @date: 2016/9/12
 */

public class Result {

    private int code;
    private String text;

    public Result() {

    }

    public Result(int resultCode) {
        this.code = resultCode;
    }

    public Result(int resultCode, String msg) {
        this.code = resultCode;
        this.text = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
