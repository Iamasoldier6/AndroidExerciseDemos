package com.iamasoldier6.recyclerviewdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Spinner mSpinner;

    private List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rc_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        //设置显示动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position,
                                               long id) {
                        if (position == 0) {
                            mRecyclerView.setLayoutManager(
                                    // 设置为线性布局
                                    new LinearLayoutManager(MainActivity.this));
                        } else if (position == 1) {
                            mRecyclerView.setLayoutManager(
                                    // 设置为表格布局
                                    new GridLayoutManager(MainActivity.this, 3));
                        } else if (position == 2) {
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        //增加测试数据
        mData.add("Recycler");
        mData.add("Recycler");
        mData.add("Recycler");

        mRecyclerAdapter = new RecyclerAdapter(mData);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerAdapter.setOnItemClickListener(
                new RecyclerAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final View view, int position) {
                        // 设置点击动画
                        view.animate()
                                .translationZ(15f).setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        view.animate()
                                                .translationZ(1f)
                                                .setDuration(500).start();
                                    }
                                }).start();
                    }
                });
    }

    public void addRecycler(View view) {
        mData.add("Recycler");
        int position = mData.size();
        if (position > 0) {
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    public void delRecycler(View view) {
        int position = mData.size();
        if (position > 0) {
            mData.remove(position - 1);
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

}
