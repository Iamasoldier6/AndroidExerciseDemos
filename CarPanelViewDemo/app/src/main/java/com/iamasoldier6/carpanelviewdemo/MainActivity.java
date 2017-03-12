package com.iamasoldier6.carpanelviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private CarPanelView mPanelView1, mPanelView2;
    private SeekBar mBar;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mPanelView1 = ( CarPanelView ) findViewById( R.id.panel_view_1 );
        mPanelView2 = ( CarPanelView ) findViewById( R.id.panel_view_2 );
        mBar = ( SeekBar ) findViewById( R.id.seek_bar );
        mPanelView1.setText( "已完成" );

        mBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser ) {
                mPanelView1.setPercent( progress );
                mPanelView2.setPercent( progress );
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ) {

            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ) {

            }
        } );
    }
}

