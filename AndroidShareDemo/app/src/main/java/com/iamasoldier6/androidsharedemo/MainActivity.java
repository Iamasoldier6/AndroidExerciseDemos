package com.iamasoldier6.androidsharedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnShare(View v) {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();

        // 关闭 sso 授权
        oks.disableSSOWhenAuthorize();

        // title 标题，印象笔记、邮箱、信息、微信、人人网和 QQ 空间等使用
        oks.setTitle("标题");

        // titleUrl 是标题的网络链接，QQ 和 QQ 空间等使用
        oks.setTitleUrl("http://sharesdk.cn");

        // text 是分享文本，所有平台都需要这个字段
        oks.setText("Testing message");

        // imagePath 是图片的本地路径，Linked-In 以外的平台都支持此参数
        // oks.setImagePath("/sdcard/test.jpg"); //确保 SDcard 下面存在此张图片
        // url 仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");

        // comment 是我对这条分享的评论，仅在人人网和 QQ 空间使用
        oks.setComment("我是测试评论文本");

        // site 是分享此内容的网站名称，仅在 QQ 空间使用
        oks.setSite(getString(R.string.app_name));

        // siteUrl 是分享此内容的网站地址，仅在 QQ 空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享 GUI
        oks.show(this);
    }
}
