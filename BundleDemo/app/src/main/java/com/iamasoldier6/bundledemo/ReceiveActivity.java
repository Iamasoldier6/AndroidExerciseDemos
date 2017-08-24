package com.iamasoldier6.bundledemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/08/23
 */
public class ReceiveActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RECEIVE_ACTIVITY";

    private Button mBtnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);

        receiveBasicData();
        receiveParcelableData();
        receiveSeriableData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back: {
                finish();
                break;
            }
            default:
                break;
        }
    }

    private void receiveBasicData() {
        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("name");
        int height = bundle.getInt("height");

        if (name != null && height != 0) {
            Log.d(TAG, "Receive basic data -- " + "name = " + name + ", height = " + height);
        }
    }

    private void receiveParcelableData() {
        Book book = (Book) getIntent().getParcelableExtra("parcelable");

        if (book != null) {
            Log.d(TAG, "Receive parcelable data -- "
                    + "Book name is: " + book.getBookName() + ", "
                    + "Author is: " + book.getAuthor() + ", "
                    + "Publish time is: " + book.getPublishTime());
        }
    }

    private void receiveSeriableData() {
        Person person = (Person) getIntent().getSerializableExtra("serializable");

        if (person != null) {
            Log.d(TAG, "Receive serializable data -- "
                    + "The name is: " + person.getName() + ", "
                    + "The age is: " + person.getAge());
        }
    }

}
