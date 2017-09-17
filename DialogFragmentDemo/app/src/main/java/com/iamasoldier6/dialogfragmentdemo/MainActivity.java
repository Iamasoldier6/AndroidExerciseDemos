package com.iamasoldier6.dialogfragmentdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnOk;
    private Button mBtnLogin;
    private Button mBtnPure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnOk = (Button) findViewById(R.id.btn_ok_dialog);
        mBtnLogin = (Button) findViewById(R.id.btn_login_dialog);
        mBtnPure = (Button) findViewById(R.id.btn_pure_alert_dialog);

        mBtnOk.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnPure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok_dialog: {
                new TestDialogFragment()
                        .show(getFragmentManager(), "TestDialogFragment");
                break;
            }
            case R.id.btn_login_dialog: {
                new LoginDialogFragment()
                        .show(getFragmentManager(), "LoginDialogFragment");
                break;
            }
            case R.id.btn_pure_alert_dialog: {
                showPureAlert();
                break;
            }
            default: {
                break;
            }
        }
    }

    private void showPureAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_login, null);
        builder.setView(view)
                .setPositiveButton("Sign in"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("Cancel", null)
                .show();
    }

}
