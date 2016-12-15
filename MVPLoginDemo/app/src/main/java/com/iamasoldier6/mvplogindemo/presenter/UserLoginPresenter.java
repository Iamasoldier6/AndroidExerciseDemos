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

    private IUserBiz mUserBiz;
    private IUserLoginView mUserLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.mUserLoginView = userLoginView;
        this.mUserBiz = new UserBiz();
    }

    public void login() {
        mUserLoginView.showLoading();
        mUserBiz.login(mUserLoginView.getUsername(), mUserLoginView.getPassword(), new OnLoginListener() {

            @Override
            public void loginSuccess(final User user) {
                // 需要在 UI 线程中执行
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        mUserLoginView.showSuccess(user);
                        mUserLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                // 需要在 UI 线程中执行
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        mUserLoginView.showFailedError();
                        mUserLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear() {
        mUserLoginView.clearUsername();
        mUserLoginView.clearPassword();
    }
}
