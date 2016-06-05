package com.iamasoldier6.topbardemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Iamasoldier6 on 6/5/16.
 */
public class TopBar extends RelativeLayout {

    // 包含 topbar 上的元素: 左按钮, 右按钮, 标题
    private Button mLeftButton, mRightButton;
    private TextView mTitleView;

    // 布局属性, 用来控制组件元素在 ViewGroup 中的位置
    private LayoutParams mLeftParams, mTitleParams, mRightParams;

    // 左按钮的属性值, 即在 atts.xml 文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    // 右按钮的属性值
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    // 标题的属性值
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    // 映射传入的接口对象
    private topbarClickListener mListener;

    public TopBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置 topbar 的背景
        setBackgroundColor(0XFFF59563);
        /**
         * 通过该方法, 将在 attrs.xml 中定义的 declare-styleable
         * 的所有属性值存储到 TypedArray 中
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        // 从 TypedArray 中取出对应的值来为要设置的属性赋值
        mLeftTextColor = typedArray.getColor(
                R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = typedArray.getDrawable(
                R.styleable.TopBar_leftBackground);
        mLeftText = typedArray.getString(
                R.styleable.TopBar_leftText);
        mRightTextColor = typedArray.getColor(
                R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = typedArray.getDrawable(
                R.styleable.TopBar_rightBackground);
        mRightText = typedArray.getString(
                R.styleable.TopBar_rightText);
        mTitleTextSize = typedArray.getDimension(
                R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = typedArray.getColor(
                R.styleable.TopBar_atitleTextColor, 0);
        mTitle = typedArray.getString(
                R.styleable.TopBar_atitle);

        /**
         * 获取完 TypedArray 的值后, 一般要调用 recycle 方法
         * 来避免重新创建时的错误
         */
        typedArray.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        /**
         * 为创建的组件元素赋值, 且值来源于在引用的 xml 文件中
         * 给对应属性的赋值
         */
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        // 为组件设置相应的布局元素
        mLeftParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
                TRUE);
        // 添加到 ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
                TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

        /**
         * 按钮的点击事件, 不需要具体的实现,
         * 只需要调用接口的方法, 回调的时候, 会有具体的实现
         */
        mRightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }

    /**
     * 暴露一个方法给调用者来注册接口回调,
     * 通过接口来获得回调者对接口方法的实现
     */
    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置按钮是否显示, 通过 id 区分按钮, flag 区分是否显示
     *
     * @param id
     * @param flag
     */
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 接口对象, 实现回调机制, 在回调方法中通过映射的接口对象调用
     * 接口中的方法, 而不用去考虑如何实现, 具体的实现由调用者去创建
     */
    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();

        // 右按钮点击事件
        void rightClick();
    }
}
