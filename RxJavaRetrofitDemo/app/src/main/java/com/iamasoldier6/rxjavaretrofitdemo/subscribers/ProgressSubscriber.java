package com.iamasoldier6.rxjavaretrofitdemo.subscribers;

import android.content.Context;
import android.widget.Toast;

import com.iamasoldier6.rxjavaretrofitdemo.progress.ProgressCancelListener;
import com.iamasoldier6.rxjavaretrofitdemo.progress.ProgressDialogHandler;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * @author: Iamasoldier6
 * @date: 07/12/2016
 *
 * Http 请求开始时，自动显示一个 ProgressDialog；
 * Http 请求结束时，关闭 ProgressDialog
 * 调用者自己对请求数据进行处理
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener mListener;
    private ProgressDialogHandler mHandler;

    private Context mContext;

    public ProgressSubscriber(SubscriberOnNextListener listener, Context context) {
        this.mListener = listener;
        this.mContext = context;
        mHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mHandler = null;
        }
    }

    // 订阅开始时调用，显示 ProgressDialog
    @Override
    public void onStart() {
        showProgressDialog();
    }

    // 完成后，隐藏 ProgressDialog
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(mContext, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    // 统一处理错误，隐藏 ProgressDialog
    @Override
    public void onError(Throwable e) {

        if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    /**
     * 将 onNext() 方法中的返回结果传给 Activity 或 Fragment 自己处理
     * @param t 创建 Subscriber 时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mListener != null) {
            mListener.onNext(t);
        }
    }

    // 取消 ProgressDialog 时，取消对 observable 的订阅，同时取消 http 的请求
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
