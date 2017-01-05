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

public class SettingFragment extends Fragment {

    private View settingFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingFragment = inflater.inflate(R.layout.text_setting, container, false);
        return settingFragment;
    }
}
