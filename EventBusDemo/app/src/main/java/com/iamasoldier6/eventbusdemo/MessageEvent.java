package com.iamasoldier6.eventbusdemo;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/16
 */
public class MessageEvent {

    private String mMessage;

    public MessageEvent() {

    }

    public MessageEvent(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
