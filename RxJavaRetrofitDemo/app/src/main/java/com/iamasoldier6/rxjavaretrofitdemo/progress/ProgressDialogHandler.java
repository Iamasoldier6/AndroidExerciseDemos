package com.iamasoldier6.rxjavaretrofitdemo.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;


/**
 * @author: Iamasoldier6
 * @date: 07/12/2016
 */

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog mDialog;

    private Context mContext;
    private boolean cancelable;
    private ProgressCancelListener mListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener listener,
                                 boolean cancelable) {
        super();
        this.mContext = context;
        this.mListener = listener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {

        if (mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(cancelable);

            if (cancelable) {
                mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mListener.onCancelProgress();
                    }
                });
            }

            if (!mDialog.isShowing()) {
                mDialog.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
            default:
                break;
        }
    }
}
