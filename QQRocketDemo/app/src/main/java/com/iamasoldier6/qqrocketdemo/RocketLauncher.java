package com.iamasoldier6.qqrocketdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Iamasoldier6 on 8/25/16.
 */
public class RocketLauncher extends LinearLayout {

    public static int width; // 记录火箭发射台的宽度
    public static int height; // 记录火箭发射台的高度
    private ImageView launcherImg; // 火箭发射台的背景图片

    public RocketLauncher(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.launcher, this);
        launcherImg = (ImageView) findViewById(R.id.iv_launcher);
        width = launcherImg.getLayoutParams().width;
        height = launcherImg.getLayoutParams().height;
    }

    /**
     * 更新火箭发射台的显示状态, 若拖小火箭到发射台上, 就显示发射
     *
     * @param isReadyToLaunch
     */
    public void updateLauncherStatus(boolean isReadyToLaunch) {
        if (isReadyToLaunch) {
            launcherImg.setImageResource(R.drawable.launcher_bg_fire);
        } else {
            launcherImg.setImageResource(R.drawable.launcher_bg_hold);
        }
    }
}
