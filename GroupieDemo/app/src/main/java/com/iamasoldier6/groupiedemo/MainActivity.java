package com.iamasoldier6.groupiedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GroupAdapter mAdapter;
    private RecyclerView mRvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvContent = findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GroupAdapter();
        // 添加非空的组内容
        mAdapter.addAll(getGroupList());
        mRvContent.setAdapter(mAdapter);
    }

    private List<Item> getGroupList() {
        List<Item> groupList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 遍历添加 ContentItem
            groupList.add(new ContentItem());
        }
        return groupList;
    }

}
