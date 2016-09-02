package com.iamasoldier6.mvplogindemo.presenter;

import android.os.Handler;

import com.iamasoldier6.mvplogindemo.model.bean.User;
import com.iamasoldier6.mvplogindemo.model.biz.IUserBiz;
import com.iamasoldier6.mvplogindemo.model.biz.OnLoginListener;
import com.iamasoldier6.mvplogindemo.model.biz.UserBiz;
import com.iamasoldier6.mvplogindemo.view.IUserLoginView;

/**
 * Created by Iamasoldier6 on 9/2/16.
 */
public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUsername(), userLoginView.getPassword(), new OnLoginListener() {

            @Override
            public void loginSuccess(final User user) {
                // 需要在 UI 线程中执行
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                // 需要在 UI 线程中执行
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear() {
        userLoginView.clearUsername();
        userLoginView.clearPassword();
    }
}
