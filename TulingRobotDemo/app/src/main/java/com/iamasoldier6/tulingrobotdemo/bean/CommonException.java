package com.iamasoldier6.tulingrobotdemo.bean;

/**
 * @author：Iamasoldier6
 * @date: 2016/9/12
 */

public class CommonException extends RuntimeException {

    public CommonException() {
        super();
    }

    public CommonException(String detailMessage) {
        super(detailMessage);
    }

    public CommonException(Throwable throwable) {
        super(throwable);
    }

    public CommonException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
