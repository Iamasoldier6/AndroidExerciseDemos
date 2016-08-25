package com.iamasoldier6.qqrocketdemo;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Iamasoldier6 on 8/24/16.
 */
public class MyWindowManager {

    private static SmallFloatWindowView smallWindow; // 小悬浮窗 View 的实例
    private static BigFloatWindowView bigWindow; // 大悬浮窗 View 的实例
    private static RocketLauncher rocketLauncher; // 火箭发射台的实例
    private static WindowManager.LayoutParams smallWindowParams; // 小悬浮窗 View 的参数
    private static WindowManager.LayoutParams bigWindowParams; // 大悬浮窗 View 的参数
    private static WindowManager.LayoutParams launcherParams; // 火箭发射台的参数
    private static WindowManager mWindowManager; // 用于控制在屏幕上添加或移除悬浮窗
    private static ActivityManager mActivityManager; // 用于获取手机可用内存

    /**
     * 创建一个小悬浮窗, 初始位置为屏幕右边中间位置
     *
     * @param context 为应用程序的 Context
     */
    public static void createSmallWindow(Context context) {
        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (smallWindow == null) {
            smallWindow = new SmallFloatWindowView(context);
            if (smallWindowParams == null) {
                smallWindowParams = new WindowManager.LayoutParams();
                smallWindowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                smallWindowParams.format = PixelFormat.RGBA_8888;
                smallWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                smallWindowParams.width = SmallFloatWindowView.windowViewWidth;
                smallWindowParams.height = SmallFloatWindowView.windowViewHeight;
                smallWindowParams.x = screenWidth;
                smallWindowParams.y = screenHeight / 2;
            }
            smallWindow.setParams(smallWindowParams);
            windowManager.addView(smallWindow, smallWindowParams);
        }
    }

    /**
     * 将小悬浮窗从屏幕上移除
     *
     * @param context 为应用程序的 Context
     */
    public static void removeSmallWindow(Context context) {
        if (smallWindow != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(smallWindow);
            smallWindow = null;
        }
    }

    /**
     * 创建一个大悬浮窗, 位置为屏幕正中间
     *
     * @param context 为应用程序的 Context
     */
    public static void createBigWindow(Context context) {
        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (bigWindow == null) {
            bigWindow = new BigFloatWindowView(context);
            if (bigWindowParams == null) {
                bigWindowParams = new WindowManager.LayoutParams();
                bigWindowParams.x = screenWidth / 2 - BigFloatWindowView.viewWidth / 2;
                bigWindowParams.y = screenHeight / 2 - BigFloatWindowView.viewHeight / 2;
                bigWindowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                bigWindowParams.format = PixelFormat.RGBA_8888;
                bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                bigWindowParams.width = BigFloatWindowView.viewWidth;
                bigWindowParams.height = BigFloatWindowView.viewHeight;
            }
            windowManager.addView(bigWindow, bigWindowParams);
        }
    }

    /**
     * 将大悬浮窗从屏幕上移除
     *
     * @param context 为应用程序的 Context
     */
    public static void removeBigWindow(Context context) {
        if (bigWindow != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(bigWindow);
            bigWindow = null;
        }
    }

    /**
     * 创建一个火箭发射台, 位置在屏幕底部
     *
     * @param context
     */
    public static void createLauncher(Context context) {
        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (rocketLauncher == null) {
            rocketLauncher = new RocketLauncher(context);
            if (launcherParams == null) {
                launcherParams = new WindowManager.LayoutParams();
                launcherParams.x = screenWidth / 2 - RocketLauncher.width / 2;
                launcherParams.y = screenHeight - RocketLauncher.height;
                launcherParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                launcherParams.format = PixelFormat.RGBA_8888;
                launcherParams.gravity = Gravity.LEFT | Gravity.TOP;
                launcherParams.width = RocketLauncher.width;
                launcherParams.height = RocketLauncher.height;
            }
            windowManager.addView(rocketLauncher, launcherParams);
        }
    }

    /**
     * 将火箭发射台从屏幕上移除
     *
     * @param context
     */
    public static void removeLauncher(Context context) {
        if (rocketLauncher != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(rocketLauncher);
            rocketLauncher = null;
        }
    }

    /**
     * 更新火箭发射台的显示状态
     */
    public static void updateLauncher() {
        if (rocketLauncher != null) {
            rocketLauncher.updateLauncherStatus(isReadyToLaunch());
        }
    }

    /**
     * 更新小悬浮窗的 TextView 上的数据, 显示内存使用的百分比
     *
     * @param context 为应用程序的 Context
     */
    public static void updateUsedPercent(Context context) {
        if (smallWindow != null) {
            TextView percentView = (TextView) smallWindow.findViewById(R.id.tv_percent);
            percentView.setText(getUsedPercentValue(context));
        }
    }

    /**
     * 是否有悬浮窗(包括小悬浮窗和大悬浮窗)显示在屏幕上
     *
     * @return 有悬浮窗显示在桌面上返回 true , 没有的话返回 false
     */
    public static boolean isWindowShowing() {
        return smallWindow != null || bigWindow != null;
    }

    /**
     * 判断小火箭是否准备好发射
     *
     * @return 火箭拖到发射台上返回 true , 否则返回 false
     */
    public static boolean isReadyToLaunch() {
        if ((smallWindowParams.x > launcherParams.x && smallWindowParams.x + smallWindowParams.width <
                launcherParams.x + launcherParams.width) && (smallWindowParams.y +
                smallWindowParams.height > launcherParams.y)) {
            return true;
        }
        return false;
    }

    /**
     * 若 WindowManager 未创建, 则创建一个新的 WindowManager 返回, 否则返回当前已创建的 WindowManager
     *
     * @param context 为应用程序的 Context
     * @return WindowManager 的实例, 用于控制在屏幕上添加或移除悬浮窗
     */
    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    /**
     * 若 ActivityManager 未创建, 则创建一个新的 ActivityManager 返回, 否则返回当前已创建的 ActivityManager
     *
     * @param context 可传入应用程序上下文
     * @return ActivityManager 的实例, 用于获取手机可用内存
     */
    private static ActivityManager getActivityManager(Context context) {
        if (mActivityManager == null) {
            mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }
        return mActivityManager;
    }

    /**
     * 计算已经使用内存的百分比, 并返回
     *
     * @param context 可传入应用程序上下文
     * @return 已使用内存的百分比, 以字符串形式返回
     */
    public static String getUsedPercentValue(Context context) {
        String dir = "/proc/meminfo";
        try {
            FileReader fileReader = new FileReader(dir);
            BufferedReader bufferedReader = new BufferedReader(fileReader, 2048);
            String memoryLine = bufferedReader.readLine();
            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
            bufferedReader.close();
            long totalMemorySize = Integer.parseInt(subMemoryLine.replaceAll("\\D+", ""));
            long availableSize = getAvailableMemory(context) / 1024;
            int percent = (int) ((totalMemorySize - availableSize) / (float) totalMemorySize * 100);
            return percent + "%";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "悬浮窗";
    }

    /**
     * 获取当前可用内存, 返回数据以字节为单位
     *
     * @param context 可传入应用程序上下文
     * @return 当前可用内存
     */
    private static long getAvailableMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        getActivityManager(context).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }
}
