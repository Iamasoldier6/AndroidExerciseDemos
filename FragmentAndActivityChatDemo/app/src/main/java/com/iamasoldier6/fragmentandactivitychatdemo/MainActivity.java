package com.iamasoldier6.fragmentandactivitychatdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements FragmentOne.ButtonOneClickListener
        , FragmentTwo.ButtonTwoClickListener {

    private FragmentOne mFragmentOne;
    private FragmentTwo mFragmentTwo;
    private FragmentThree mFragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mFragmentOne = new FragmentOne();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_content, mFragmentOne, "ONE");
        transaction.commit();
    }

    /**
     * FragmentOne 中点击按钮的回调
     */
    @Override
    public void onButtonOneClick() {
        if (mFragmentTwo == null) {
            mFragmentTwo = new FragmentTwo();
            mFragmentTwo.setButtonTwoClickListener(this);
        }

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_content, mFragmentTwo, "TWO");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * FragmentTwo 中点击按钮时的回调
     */
    @Override
    public void onButtonTwoClick() {
        if (mFragmentThree == null) {
            mFragmentThree = new FragmentThree();
        }

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(mFragmentTwo);
        transaction.add(R.id.fl_content, mFragmentThree, "THREE");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
