package com.iamasoldier6.fragmentbackstackdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/04
 */
public class FragmentThree extends Fragment implements View.OnClickListener {

    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        mButton = (Button) view.findViewById(R.id.btn_three);
        mButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "You clicked the button in FragmentThree", Toast.LENGTH_SHORT).show();
    }

}
