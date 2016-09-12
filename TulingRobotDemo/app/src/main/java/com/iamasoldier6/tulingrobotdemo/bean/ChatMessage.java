package com.iamasoldier6.tulingrobotdemo.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author：Iamasoldier6
 * @date: 2016/9/12
 * <p>
 * 返回的消息
 */

public class ChatMessage {

    private Type type; // 消息类型
    private String msg; // 消息内容
    private Date date; // 日期
    private String dateStr; // 日期的字符串格式
    private String name; // 发送人

    public enum Type {
        INPUT, OUTPUT
    }

    public ChatMessage() {

    }

    public ChatMessage(Type type, String msg) {
        super();
        this.type = type;
        this.msg = msg;
        setDate(new Date());
    }

    public String getDateStr() {
        return dateStr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateStr = df.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }
}
