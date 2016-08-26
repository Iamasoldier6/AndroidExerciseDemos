package com.iamasoldier6.pulldownrefreshdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    RefreshView refreshView;
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] items = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        refreshView = (RefreshView) findViewById(R.id.refresh_view);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        refreshView.setOnRefreshListener(new RefreshView.PullDownRefreshListener() {

            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshView.finishRefresh();
            }
        }, 0);
    }
}
