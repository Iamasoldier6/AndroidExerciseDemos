package com.iamasoldier6.bmobserdemo;

import cn.bmob.v3.BmobObject;

/**
 * Created by Iamasoldier6 on 6/10/16.
 */
public class Feedback extends BmobObject {

    private String name;
    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
