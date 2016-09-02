package com.iamasoldier6.mvplogindemo.model.biz;

/**
 * Created by Iamasoldier6 on 9/2/16.
 */
public interface IUserBiz {

    void login(String username, String password, OnLoginListener loginListener);
}
