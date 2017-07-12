package com.iamasoldier6.originviewpagerdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/12
 */
public class PageOneView extends PageView {

  public PageOneView( Context context ) {
    super( context );
    initView( context );
  }

  public PageOneView( Context context, AttributeSet attrs ) {
    super( context, attrs );
    initView( context );
  }

  public PageOneView( Context context, AttributeSet attrs, int defStyleAttr ) {
    super( context, attrs, defStyleAttr );
    initView( context );
  }

  private void initView( Context context ) {
    View view = LayoutInflater.from( context ).inflate( R.layout.view_page, null );
    TextView textView = ( TextView ) view.findViewById( R.id.tv_show );
    textView.setText( "Page-One" );
    addView( view );
  }

}
