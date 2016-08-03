package com.iamasoldier6.toastimprodemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Iamasoldier6 on 8/3/16.
 */
public class Util {
    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
