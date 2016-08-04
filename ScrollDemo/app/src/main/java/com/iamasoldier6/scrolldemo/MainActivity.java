package com.iamasoldier6.scrolldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout liLayout;

    private Button mToButton;
    private Button mByButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liLayout = (LinearLayout) findViewById(R.id.liLayout);
        mToButton = (Button) findViewById(R.id.toButton);
        mByButton = (Button) findViewById(R.id.byButton);

        mToButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                liLayout.scrollTo(-60, -100);
//                mToButton.scrollTo(-60, -100);
            }
        });

        mByButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                liLayout.scrollBy(-60, -100);
//                mByButton.scrollBy(-60, -100);
            }
        });
    }
}
