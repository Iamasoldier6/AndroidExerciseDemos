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

public class NewsFragment extends Fragment {

    private View newsFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsFragment = inflater.inflate(R.layout.text_news, container, false);
        return newsFragment;
    }
}
