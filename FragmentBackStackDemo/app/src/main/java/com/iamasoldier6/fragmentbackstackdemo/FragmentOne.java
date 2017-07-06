package com.iamasoldier6.fragmentbackstackdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/03
 */
public class FragmentOne extends Fragment implements View.OnClickListener {

    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mButton = (Button) view.findViewById(R.id.btn_one);
        mButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentTwo fragmentTwo = new FragmentTwo();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_content, fragmentTwo, "TWO");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
