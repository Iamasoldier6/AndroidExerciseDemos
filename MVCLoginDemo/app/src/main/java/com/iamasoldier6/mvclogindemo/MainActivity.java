package com.iamasoldier6.mvclogindemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iamasoldier6.mvclogindemo.model.bean.User;
import com.iamasoldier6.mvclogindemo.model.biz.IUserBiz;
import com.iamasoldier6.mvclogindemo.model.biz.OnLoginListener;
import com.iamasoldier6.mvclogindemo.model.biz.UserBiz;

public class MainActivity extends Activity {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private Button mBtnClear;
    private ProgressBar mPbLoad;
    public IUserBiz mUserBiz;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.mUserBiz = new UserBiz(); // 不要遗漏, 否则报空指针异常
        initView();
    }

    private void initView() {
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnClear = (Button) findViewById(R.id.btn_clear);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mPbLoad = (ProgressBar) findViewById(R.id.pb_load);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    public void login() {
        showLoading();
        mUserBiz.login(getUsername(), getPassword(), new OnLoginListener() {

            @Override
            public void loginSuccess(final User user) {
                // 需要在 UI 线程中执行
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        showSuccess(user);
                        hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                // 需要在 UI 线程中执行
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        showFailedError();
                        hideLoading();
                    }
                });
            }
        });
    }

    public void clear() {
        clearUsername();
        clearPassword();
    }

    public String getUsername() {
        return mEtUsername.getText().toString();
    }

    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    public void clearUsername() {
        mEtUsername.setText("");
    }

    public void clearPassword() {
        mEtPassword.setText("");
    }

    public void showLoading() {
        mPbLoad.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mPbLoad.setVisibility(View.GONE);
    }

    public void showSuccess(User user) {
        Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
    }

    public void showFailedError() {
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }
}
