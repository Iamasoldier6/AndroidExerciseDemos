package com.iamasoldier6.daggertwodemo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author: Iamasoldier6
 * @date: 10/12/2016
 */

public class ToastUtil {

    private Context mContext;

    public ToastUtil(Context context) {
        this.mContext = context;
    }

    public void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
