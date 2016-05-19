package com.iamasoldier6.chatlistviewdemo;

import android.graphics.Bitmap;

/**
 * Created by Iamasoldier6 on 5/19/16.
 */
public class ChatBean {

    private int type;
    private String text;
    private Bitmap icon;

    public ChatBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

}
