package com.iamasoldier6.webviewdemo;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

@SuppressLint("JavascriptInterface")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "WebViewTAG";

    private WebView mWebView;
    private Button sendBtn;
    private Handler mHandler = new Handler();

    private MediaPlayer mMediaPlayer;//音频

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webview);
        sendBtn = (Button) findViewById(R.id.btn_send);
        sendBtn.setOnClickListener(this);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
        mWebView.loadUrl("file:///android_asset/hello.html");

        setVolumeControlStream(AudioManager.STREAM_MUSIC);//设置音频流 - STREAM_MUSIC：音乐回放即媒体音量
        mMediaPlayer = MediaPlayer.create(this, R.raw.aaaass);//实例化 MediaPlayer 并添加音频

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });

        mMediaPlayer.start();//开始播放
        mMediaPlayer.setLooping(true);//循环播放
    }

    final class DemoJavaScriptInterface {

        DemoJavaScriptInterface() {

        }

        /**
         * This is not called on the UI thread. Post a runnable to invoke
         * loadUrl on the UI thread.
         */
        public void clickOnAndroid() {
            mHandler.post(new Runnable() {
                public void run() {
                    mWebView.loadUrl("javascript:wave()");
                }
            });

        }
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for
     * debugging your javascript.
     */
    final class MyWebChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(LOG_TAG, message);
            result.confirm();
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                mWebView.evaluateJavascript("aa(" + Visualizer.getCaptureSizeRange()[1] + ");", null);
                mWebView.evaluateJavascript("aa(" + mMediaPlayer.getAudioSessionId() + ");", null);
                Log.d("my", "MediaPlayer audio session ID: " + mMediaPlayer.getAudioSessionId());
                Log.d("my", "max size " + Visualizer.getCaptureSizeRange()[1]
                        + "-----minium size " + Visualizer.getCaptureSizeRange()[0]);
        }
    }

}
