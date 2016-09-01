package com.iamasoldier6.fragtabdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Iamasoldier6 on 9/1/16.
 */
public class NewsFragment extends Fragment {

    private View newsFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsFragment = inflater.inflate(R.layout.fragment_news, container, false);
        return newsFragment;
    }
}
