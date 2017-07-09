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
public class FragmentTwo extends Fragment implements View.OnClickListener {

    private Button mButton;
    private ButtonTwoClickListener mClickListener;

    /**
     * 设置回调接口
     *
     * @param clickListener
     */
    public void setButtonTwoClickListener(ButtonTwoClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        mButton = (Button) view.findViewById(R.id.btn_two);
        mButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (mClickListener != null) {
            mClickListener.onButtonTwoClick();
        }
    }

    public interface ButtonTwoClickListener {
        void onButtonTwoClick();
    }

}
