package com.iamasoldier6.mvvmgithubdemo.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.iamasoldier6.mvvmgithubdemo.BR;

/**
 * @author: Iamasoldier6
 * @date: 10/1/16.
 */

public class Contributor extends BaseObservable {

    private String login;
    private int contributions;

    @Bindable
    public String getLogin() {
        return login;
    }

    @Bindable
    public int getContributions() {
        return contributions;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login); // 通知 View 更新
    }

    public void setContributioins(int contributions) {
        this.contributions = contributions;
        notifyPropertyChanged(BR.contributions);
    }

    @Override
    public String toString() {
        return login + ", " + contributions;
    }
}
