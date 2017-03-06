package com.iamasoldier6.percentcircleviewdemo;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author: Iamasoldier6
 * @date: 06/03/2017
 */

public class PxUtils {

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

}
