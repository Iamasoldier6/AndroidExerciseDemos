package com.iamasoldier6.originviewpagerdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/12
 */
public abstract class PageView extends RelativeLayout {

    public PageView( Context context ) {
        super( context );
    }

    public PageView( Context context, AttributeSet attrs ) {
        super( context, attrs );
    }

    public PageView( Context context, AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
    }

}
