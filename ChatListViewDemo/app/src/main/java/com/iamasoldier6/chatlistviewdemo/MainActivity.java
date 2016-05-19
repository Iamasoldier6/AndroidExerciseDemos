package com.iamasoldier6.chatlistviewdemo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        ChatBean bean1 = new ChatBean();
        bean1.setType(0);
        bean1.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.beauty));
        bean1.setText("Hello, how are you?");

        ChatBean bean2 = new ChatBean();
        bean2.setType(1);
        bean2.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.cat));
        bean2.setText("Fine, thank you, and you?");

        ChatBean bean3 = new ChatBean();
        bean3.setType(0);
        bean3.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.beauty));
        bean3.setText("I'm fine, too.");

        ChatBean bean4 = new ChatBean();
        bean4.setType(1);
        bean4.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.cat));
        bean4.setText("Nice to meet you.");

        ChatBean bean5 = new ChatBean();
        bean5.setType(0);
        bean5.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.beauty));
        bean5.setText("Nice to meet you, too.");

        List<ChatBean> data = new ArrayList<ChatBean>();
        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        data.add(bean4);
        data.add(bean5);

        mListView.setAdapter(new ChatAdapter(this, data));
    }

}
