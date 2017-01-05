package com.iamasoldier6.fragtabviewpagerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author: Iamasoldier6
 * @date: 05/01/2017
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private View messageLayout; // 消息界面
    private View contactsLayout; // 联系人界面
    private View newsLayout; // 动态界面
    private View settingLayout; // 设置界面

    private ImageView messageImg; // Tab 布局上显示消息图标的控件
    private ImageView contactsImg; // Tab 布局上显示联系人图标的控件
    private ImageView newsImg; // Tab 布局上显示动态图标的控件
    private ImageView settingImg; // Tab 布局上显示设置图标的控件

    private TextView messageTxt; // Tab 布局上显示消息标题的控件
    private TextView contactsTxt; // Tab 布局上显示联系人标题的控件
    private TextView newsTxt; // Tab 布局上显示动态标题的控件
    private TextView settingTxt; // Tab 布局上显示设置标题的控件

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); // 初始化布局元素
        final ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MessageFragment());
        fragmentList.add(new ContactsFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new SettingFragment());

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOnPageChangeListener(this);
        setTabSelection(0); // 第一次启动时选中第 1 个 Tab ,即消息 Tab
    }

    /**
     * 获取到所需控件的实例, 设置好点击事件
     */
    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.vp_content);

        messageLayout = findViewById(R.id.rl_message_layout);
        contactsLayout = findViewById(R.id.rl_contacts_layout);
        newsLayout = findViewById(R.id.rl_news_layout);
        settingLayout = findViewById(R.id.rl_setting_layout);

        messageImg = (ImageView) findViewById(R.id.iv_message);
        contactsImg = (ImageView) findViewById(R.id.iv_contacts);
        newsImg = (ImageView) findViewById(R.id.iv_news);
        settingImg = (ImageView) findViewById(R.id.iv_setting);

        messageTxt = (TextView) findViewById(R.id.tv_message);
        contactsTxt = (TextView) findViewById(R.id.tv_contacts);
        newsTxt = (TextView) findViewById(R.id.tv_news);
        settingTxt = (TextView) findViewById(R.id.tv_setting);

        messageLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_message_layout:
                setTabSelection(0); // 消息 Tab
                viewPager.setCurrentItem(0);
                break;
            case R.id.rl_contacts_layout:
                setTabSelection(1); // 联系人 Tab
                viewPager.setCurrentItem(1);
                break;
            case R.id.rl_news_layout:
                setTabSelection(2); // 动态 Tab
                viewPager.setCurrentItem(2);
                break;
            case R.id.rl_setting_layout:
                setTabSelection(3); // 设置 Tab
                viewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    /**
     * 由传入的 index 参数来设置选中的 Tab 页
     *
     * @param index 每个 Tab 页对应的下标, 0 消息, 1 联系人, 2 动态, 3 设置
     */
    private void setTabSelection(int index) {
        clearSelection(); // 每次选中之前, 清除掉上次的选中状态
        switch (index) {
            case 0:
                // 当点击消息 Tab 时, 改变控件的图片和文字颜色
                messageImg.setImageResource(R.drawable.message_selected);
                messageTxt.setTextColor(Color.WHITE);
                break;
            case 1:
                // 当点击联系人 Tab 时, 改变控件的图片和文字颜色
                contactsImg.setImageResource(R.drawable.contacts_selected);
                contactsTxt.setTextColor(Color.WHITE);
                break;
            case 2:
                // 当点击动态 Tab 时, 改变控件的图片和文字颜色
                newsImg.setImageResource(R.drawable.news_selected);
                newsTxt.setTextColor(Color.WHITE);
                break;
            case 3:
            default:
                // 当点击设置 Tab 时, 改变控件的图片和文字颜色
                settingImg.setImageResource(R.drawable.setting_selected);
                settingTxt.setTextColor(Color.WHITE);
                break;
        }
    }

    /**
     * 清除掉所有的选中状态
     */
    private void clearSelection() {
        messageImg.setImageResource(R.drawable.message_unselected);
        messageTxt.setTextColor(Color.parseColor("#82858B"));
        contactsImg.setImageResource(R.drawable.contacts_unselected);
        contactsTxt.setTextColor(Color.parseColor("#82858B"));
        newsImg.setImageResource(R.drawable.news_unselected);
        newsTxt.setTextColor(Color.parseColor("#82858B"));
        settingImg.setImageResource(R.drawable.setting_unselected);
        settingTxt.setTextColor(Color.parseColor("#82858B"));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTabSelection(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
