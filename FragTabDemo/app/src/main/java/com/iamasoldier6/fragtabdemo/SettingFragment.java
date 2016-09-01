package com.iamasoldier6.fragtabdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Iamasoldier6 on 9/1/16.
 */
public class SettingFragment extends Fragment {

    private View settingFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingFragment = inflater.inflate(R.layout.fragment_setting, container, false);
        return settingFragment;
    }
}
