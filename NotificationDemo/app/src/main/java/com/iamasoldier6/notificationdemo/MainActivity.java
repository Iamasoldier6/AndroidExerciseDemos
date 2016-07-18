package com.iamasoldier6.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private int NOTIFICATION_ID_BASIC = 1;
    private int NOTIFICATION_ID_COLLAPSE = 2;
    private int NOTIFICATION_ID_HEADSUP = 3;
    private int NOTIFICATION_ID_VISIBILITY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void basicNotify(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.baidu.com"));
        // 构造 PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);
        // 创建 Notification 对象
        Notification.Builder builder = new Notification.Builder(this);
        // 设置 Notification 的各种属性
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true); // 系统提供的移除通知操作
        builder.setLargeIcon(BitmapFactory.decodeResource(
                getResources(), R.drawable.ic_launcher));
        builder.setContentTitle("Basic Notifications");
        builder.setContentText("I am a basic notification");
        builder.setSubText("It is really basic");
        // 通过 NotificationManager 来发出 Notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_BASIC, builder.build());
    }

    public void collapseNotify(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.sina.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(
                getResources(), R.drawable.ic_launcher));
        // 通过 RemoteViews 来创建自定义的 Notification 视图
        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.notification);
        contentView.setTextViewText(R.id.textView, "Show me when collapsed");
        Notification notification = builder.build();
        notification.contentView = contentView; // 指定为正常状态下的视图
        // 通过 RemoteViews 来创建自定义的 Notification 视图
        RemoteViews expandedView = new RemoteViews(getPackageName(),
                R.layout.notification_expanded);
        notification.bigContentView = expandedView; // 指定为展开时的视图

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_COLLAPSE, notification);
    }

    public void headsupNotify(View view) {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Headsup Notification")
                .setContentText("I am a Headsup notification");

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, push, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentText("Heads-Up notification on Android 5.0")
                .setFullScreenIntent(pendingIntent, true);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_HEADSUP, builder.build());
    }

    public void visibilityNotify(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(
                R.id.visibility_radio_group);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("Notification for visibility test");
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_button_public:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("Public");
                builder.setSmallIcon(R.drawable.ic_public);
                break;
            case R.id.radio_button_private:
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("Private");
                builder.setSmallIcon(R.drawable.ic_private);
                break;
            case R.id.radio_button_secret:
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("Secret");
                builder.setSmallIcon(R.drawable.ic_secret);
                break;
            default:
                break;
        }
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_VISIBILITY, builder.build());
    }
}
