package com.iamasoldier6.fragtabdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MessageFragment mMessageFragment; // 消息
    private ContactsFragment mContactsFragment; // 联系人
    private NewsFragment mNewsFragment; // 动态
    private SettingFragment mSettingFragment; // 设置
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
    private FragmentManager fragmentManager; // 对 Fragment 管理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView(); // 初始化布局元素
        fragmentManager = getFragmentManager();
        setTabSelection(0); // 第一次启动时选中第 1 个 Tab ,即消息 Tab
    }

    /**
     * 获取到所需控件的实例, 设置好点击事件
     */
    private void initView() {
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
                break;
            case R.id.rl_contacts_layout:
                setTabSelection(1); // 联系人 Tab
                break;
            case R.id.rl_news_layout:
                setTabSelection(2); // 动态 Tab
                break;
            case R.id.rl_setting_layout:
                setTabSelection(3); // 设置 Tab
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
        FragmentTransaction transaction = fragmentManager.beginTransaction(); // 开启一个 Fragment 事物
        hideFragments(transaction); // 隐藏掉所有的 Fragment , 以防多个 Fragment 显示在界面上
        switch (index) {
            case 0:
                // 当点击消息 Tab 时, 改变控件的图片和文字颜色
                messageImg.setImageResource(R.drawable.message_selected);
                messageTxt.setTextColor(Color.WHITE);
                if (mMessageFragment == null) {
                    // 若 MessageFragment 为空, 则创建一个并添加到界面上
                    mMessageFragment = new MessageFragment();
                    transaction.add(R.id.fl_content, mMessageFragment);
                } else {
                    // 若 MessageFragment 不为空, 则直接显示
                    transaction.show(mMessageFragment);
                }
                break;
            case 1:
                // 当点击联系人 Tab 时, 改变控件的图片和文字颜色
                contactsImg.setImageResource(R.drawable.contacts_selected);
                contactsTxt.setTextColor(Color.WHITE);
                if (mContactsFragment == null) {
                    // 若 ContactsFragment 为空, 则创建一个并添加到界面上
                    mContactsFragment = new ContactsFragment();
                    transaction.add(R.id.fl_content, mContactsFragment);
                } else {
                    // 若 ContactsFragment 不为空, 则直接显示
                    transaction.show(mContactsFragment);
                }
                break;
            case 2:
                // 当点击动态 Tab 时, 改变控件的图片和文字颜色
                newsImg.setImageResource(R.drawable.news_selected);
                newsTxt.setTextColor(Color.WHITE);
                if (mNewsFragment == null) {
                    // 若 NewsFragment 为空, 则创建一个并添加到界面上
                    mNewsFragment = new NewsFragment();
                    transaction.add(R.id.fl_content, mNewsFragment);
                } else {
                    // 若 NewsFragment 不为空, 则直接显示
                    transaction.show(mNewsFragment);
                }
                break;
            case 3:
            default:
                // 当点击设置 Tab 时, 改变控件的图片和文字颜色
                settingImg.setImageResource(R.drawable.setting_selected);
                settingTxt.setTextColor(Color.WHITE);
                if (mSettingFragment == null) {
                    // 若 SettingFragment 为空, 则创建一个并添加到界面上
                    mSettingFragment = new SettingFragment();
                    transaction.add(R.id.fl_content, mSettingFragment);
                } else {
                    // 若 SettingFragment 不为空, 则直接显示
                    transaction.show(mSettingFragment);
                }
                break;
        }
        transaction.commit();
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

    /**
     * 将所有的 Fragment 设置为隐藏状态
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mMessageFragment != null) {
            transaction.hide(mMessageFragment);
        }
        if (mContactsFragment != null) {
            transaction.hide(mContactsFragment);
        }
        if (mNewsFragment != null) {
            transaction.hide(mNewsFragment);
        }
        if (mSettingFragment != null) {
            transaction.hide(mSettingFragment);
        }
    }
}
