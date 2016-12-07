package com.iamasoldier6.rxjavaretrofitdemo.http;

/**
 * @author: Iamasoldier6
 * @date: 07/12/2016
 */

public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 100;
    public static final int PASSWORD_WRONG = 101;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    private static String getApiExceptionMessage(int code) {

        String message = "";
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case PASSWORD_WRONG:
                message = "密码错误";
                break;
            default:
                message = "未知错误";
                break;
        }
        return message;
    }
}
