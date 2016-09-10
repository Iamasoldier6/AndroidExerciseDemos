package com.iamasoldier6.mvclogindemo.model.biz;

/**
 * Created by Iamasoldier6 on 9/10/16.
 */
public interface IUserBiz {

    void login(String username, String password, OnLoginListener loginListener);
}
