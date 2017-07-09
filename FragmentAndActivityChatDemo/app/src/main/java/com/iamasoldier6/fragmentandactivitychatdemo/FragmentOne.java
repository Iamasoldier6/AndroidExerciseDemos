package com.iamasoldier6.fragmentandactivitychatdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/09
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

    /**
     * 转交给宿主 Activity 处理
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (getActivity() instanceof ButtonOneClickListener) {
            ((ButtonOneClickListener) getActivity()).onButtonOneClick();
        }
    }

    /**
     * 按钮点击回调
     */
    public interface ButtonOneClickListener {
        void onButtonOneClick();
    }

}
