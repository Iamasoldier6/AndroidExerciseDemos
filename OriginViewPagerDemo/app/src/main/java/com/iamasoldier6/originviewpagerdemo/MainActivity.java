package com.iamasoldier6.originviewpagerdemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/12
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<PageView> mPageList;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initData();
        initView();
    }

    private void initData() {
        mPageList = new ArrayList<>();
        mPageList.add( new PageOneView( MainActivity.this ) );
        mPageList.add( new PageTwoView( MainActivity.this ) );
        mPageList.add( new PageThreeView( MainActivity.this ) );
    }

    private void initView() {
        mViewPager = ( ViewPager ) findViewById( R.id.vp_page );
        mViewPager.setAdapter( new SimplePagerAdapter() );
    }

    private class SimplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object ) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position ) {
            container.addView( mPageList.get( position ) );
            return mPageList.get( position );
        }

        @Override
        public void destroyItem( ViewGroup container, int position, Object object ) {
            container.removeView( ( View ) object );
        }
    }

}

