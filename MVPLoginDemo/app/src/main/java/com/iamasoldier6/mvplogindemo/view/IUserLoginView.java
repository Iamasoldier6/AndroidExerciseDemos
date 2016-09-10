package com.iamasoldier6.mvplogindemo.view;

import com.iamasoldier6.mvplogindemo.model.bean.User;

/**
 * Created by Iamasoldier6 on 9/2/16.
 */
public interface IUserLoginView {

    String getUsername();

    String getPassword();

    void clearUsername();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void showSuccess(User user);

    void showFailedError();
}
