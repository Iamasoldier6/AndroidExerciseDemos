package com.iamasoldier6.fragtabdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Iamasoldier6 on 9/1/16.
 */
public class MessageFragment extends Fragment {

    private View messageFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        messageFragment = inflater.inflate(R.layout.fragment_message, container, false);
        return messageFragment;
    }
}
