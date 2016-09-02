package com.iamasoldier6.mvplogindemo.model.biz;

import com.iamasoldier6.mvplogindemo.model.bean.User;

/**
 * Created by Iamasoldier6 on 9/2/16.
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
