package com.iamasoldier6.fragmentlifecycledemo;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author: Iamasoldier6
 * @date: 2017/06/24
 */
public class MainActivity extends AppCompatActivity {

    private SubFragment mSubFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        mSubFragment = (SubFragment) manager.findFragmentById(R.id.fragment_sub);

        if (mSubFragment == null) {
            mSubFragment = new SubFragment();
            manager.beginTransaction().add(R.id.activity_main, mSubFragment).commit();
        }
    }

}
