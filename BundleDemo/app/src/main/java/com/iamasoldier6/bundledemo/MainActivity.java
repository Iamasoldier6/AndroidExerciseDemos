package com.iamasoldier6.bundledemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MAIN_ACTIVITY";

    private Button mBtnBasic;
    private Button mBtnParcelable;
    private Button mBtnSerializable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnBasic = (Button) findViewById(R.id.btn_basic);
        mBtnParcelable = (Button) findViewById(R.id.btn_parcelable);
        mBtnSerializable = (Button) findViewById(R.id.btn_serializable);

        mBtnBasic.setOnClickListener(this);
        mBtnParcelable.setOnClickListener(this);
        mBtnSerializable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_basic:
                sendBasicBundleData();
                break;
            case R.id.btn_parcelable:
                sendParcelableBundleData();
                break;
            case R.id.btn_serializable:
                sendSerializableBundleData();
                break;
            default:
                break;
        }
    }

    private void sendBasicBundleData() {
        Intent intent = new Intent(this, ReceiveActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("name", "Iamasoldier6");
        bundle.putInt("height", 176);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void sendParcelableBundleData() {
        Intent intent = new Intent(this, ReceiveActivity.class);

        Book book = new Book();
        book.setBookName("Android");
        book.setAuthor("Iamasoldier6");
        book.setPublishTime(2017);

        Bundle bundle = new Bundle();
        bundle.putParcelable("parcelable", book);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void sendSerializableBundleData() {
        Intent intent = new Intent(this, ReceiveActivity.class);

        Person person = new Person();
        person.setName("Iamasoldier6");
        person.setAge(24);

        Bundle bundle = new Bundle();
        bundle.putSerializable("serializable", person);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}
