package com.iamasoldier6.spannablestringdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTvNormal;
    private TextView mTvLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvNormal = findViewById(R.id.tv_text_normal);
        mTvLink = findViewById(R.id.tv_text_link);

        mTvNormal.setText(getNormalString());
        mTvNormal.setMovementMethod(LinkMovementMethod.getInstance());

        mTvLink.setText(getLinkString());
        mTvLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private SpannableString getNormalString() {
        SpannableString ssNormal = new SpannableString("Hi，我是吉尔伯特·阿里纳斯，这是我的故事。 " +
                "职业生涯的前 40 场，我是在板凳上度过的。 他们认为我打不上比赛， 我想，他们根本没看到我的天赋。 " +
                "他们觉得我就是个 0，一无是处。 但是我并没有坐在那里怨天尤人，而是不断的训练，训练。 " +
                "在没有人相信你的时候，你的任何努力都会为自己加分。 这已经不是我能否打好篮球的问题了， 而是我要证明他们是错误的。 " +
                "现在我仍然穿着 0 号球衣，因为我要告诫自己每天都要努力。");

        // 设置字体: default, default-bold, monospace, serif, sans-serif
        ssNormal.setSpan(new TypefaceSpan("default"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new TypefaceSpan("default-bold"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new TypefaceSpan("monospace"), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new TypefaceSpan("serif"), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new TypefaceSpan("sans-serif"), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置字体大小
        // 绝对值, 20 为返回的物理像素
        ssNormal.setSpan(new AbsoluteSizeSpan(20), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 绝对值, 为 true, 则返回独立像素
        ssNormal.setSpan(new AbsoluteSizeSpan(20, true), 12, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 相对值, 0.5f 表示为默认字体大小的一半
        ssNormal.setSpan(new RelativeSizeSpan(0.5f), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置字体前景色
        ssNormal.setSpan(new ForegroundColorSpan(Color.RED), 16, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置字体背景色
        ssNormal.setSpan(new BackgroundColorSpan(Color.GREEN), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置字体样式: NORMAL, BOLD, ITALIC, BOLD_ITALIC
        ssNormal.setSpan(new StyleSpan(Typeface.NORMAL), 20, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new StyleSpan(Typeface.BOLD), 21, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new StyleSpan(Typeface.ITALIC), 22, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 23, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置下划线
        ssNormal.setSpan(new UnderlineSpan(), 24, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置删除线
        ssNormal.setSpan(new StrikethroughSpan(), 26, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置上下标
        ssNormal.setSpan(new SubscriptSpan(), 28, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssNormal.setSpan(new SuperscriptSpan(), 30, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置字体, 2.0f 为默认字体宽度的两倍, X 轴方向放大, 高度不变
        ssNormal.setSpan(new ScaleXSpan(2.0f), 32, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置项目符号
        ssNormal.setSpan(new BulletSpan(BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN), 0, ssNormal.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置图片
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_foreground);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ssNormal.setSpan(new ImageSpan(drawable), 44, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "我是可以点击的喔", Toast.LENGTH_SHORT).show();
            }
        };
        ssNormal.setSpan(clickableSpan, ssNormal.length() - 29, ssNormal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ssNormal;
    }

    private SpannableString getLinkString() {
        SpannableString ssLink = new SpannableString("电话  邮件  百度一下  短信  彩信  进入地图");

        // 电话
        ssLink.setSpan(new URLSpan("tel:8008820"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 邮件
        ssLink.setSpan(new URLSpan("mailto:zebron623@163.com"), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 网络
        ssLink.setSpan(new URLSpan("http://www.baidu.com"), 8, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 短信
        ssLink.setSpan(new URLSpan("sms:10086"), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 彩信
        ssLink.setSpan(new URLSpan("mms:10086"), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 地图
        ssLink.setSpan(new URLSpan("geo:32.123456,-17.123456"), 22, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ssLink;
    }
}
