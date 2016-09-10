package com.iamasoldier6.mvclogindemo.model.biz;

import com.iamasoldier6.mvclogindemo.model.bean.User;

/**
 * Created by Iamasoldier6 on 9/10/16.
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
