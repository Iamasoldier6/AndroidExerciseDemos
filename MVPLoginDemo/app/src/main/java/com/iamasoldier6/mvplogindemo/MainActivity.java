package com.iamasoldier6.mvplogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iamasoldier6.mvplogindemo.model.bean.User;
import com.iamasoldier6.mvplogindemo.presenter.UserLoginPresenter;
import com.iamasoldier6.mvplogindemo.view.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView {

    private EditText usernameEdt;
    private EditText passwordEdt;
    private Button loginBtn;
    private Button clearBtn;
    private ProgressBar loadPb;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        usernameEdt = (EditText) findViewById(R.id.et_username);
        passwordEdt = (EditText) findViewById(R.id.et_password);
        clearBtn = (Button) findViewById(R.id.btn_clear);
        loginBtn = (Button) findViewById(R.id.btn_login);
        loadPb = (ProgressBar) findViewById(R.id.pb_load);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUsername() {
        return usernameEdt.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEdt.getText().toString();
    }

    @Override
    public void clearUsername() {
        usernameEdt.setText("");
    }

    @Override
    public void clearPassword() {
        passwordEdt.setText("");
    }

    @Override
    public void showLoading() {
        loadPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadPb.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() + " Login Success, To MainActivity",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
    }
}
