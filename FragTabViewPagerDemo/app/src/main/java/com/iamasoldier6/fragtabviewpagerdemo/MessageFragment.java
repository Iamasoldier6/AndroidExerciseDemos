package com.iamasoldier6.fragtabviewpagerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: Iamasoldier6
 * @date: 05/01/2017
 */

public class MessageFragment extends Fragment {

    private View messageFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        messageFragment = inflater.inflate(R.layout.text_message, container, false);
        return messageFragment;
    }
}
