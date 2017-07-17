package com.iamasoldier6.eventbusdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/16
 */
public class FragmentOne extends Fragment implements View.OnClickListener {

    private Button mBtnOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mBtnOne = (Button) view.findViewById(R.id.btn_left);
        mBtnOne.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                EventBus.getDefault().post(new MessageEvent("Fragment 之间通信成功！"));
                break;
            default:
                break;
        }
    }

}
