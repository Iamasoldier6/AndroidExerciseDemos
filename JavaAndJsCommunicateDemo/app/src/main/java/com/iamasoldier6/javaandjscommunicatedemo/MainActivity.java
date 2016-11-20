package com.iamasoldier6.javaandjscommunicatedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private LinearLayout llLayout;
    private EditText etInput;
    private String url = "file:///android_asset/JsSample.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llLayout = (LinearLayout) findViewById(R.id.ll_root);
        etInput = (EditText) findViewById(R.id.et_input);
        initView();
    }

    private void initView() {

        // 动态创建 WebView 添加到整体布局中
        mWebView = new WebView(getApplication());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        llLayout.addView(mWebView);
        // 当前 WebView 展示，不跳转到系统浏览器
        mWebView.setWebViewClient(new WebViewClient() {

        });

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.loadUrl(url);
        mWebView.addJavascriptInterface(new JsInterface(), "Java");
    }

    public void confirm(View view) {
        // Java 调用 JS
        mWebView.loadUrl("javascript:javaCallJs(" + "'" + etInput.getText().toString() + "'" + ")");
    }

    // 页面销毁时，移除 WebView
    @Override
    protected void onDestroy() {
        super.onDestroy();
        llLayout.removeView(mWebView);
        mWebView.stopLoading();
        mWebView.removeAllViews();
        mWebView.destroy();
    }

    private class JsInterface {
        // JS 调用 Java
        @JavascriptInterface
        public void showAlert(String str) {
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
        }
    }
}
