package com.iamasoldier6.tulingrobotdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.iamasoldier6.tulingrobotdemo.bean.ChatMessage;
import com.iamasoldier6.tulingrobotdemo.util.HttpUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mChatView; // 展示对话消息
    private EditText mMessageEdit; // 文本域
    private List<ChatMessage> mDatas = new ArrayList<ChatMessage>(); // 存储对话消息
    private ChatMessageAdapter mAdapter;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            ChatMessage from = (ChatMessage) msg.obj;
            mDatas.add(from);
            mAdapter.notifyDataSetChanged();
            mChatView.setSelection(mDatas.size() - 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        mAdapter = new ChatMessageAdapter(this, mDatas);
        mChatView.setAdapter(mAdapter);
    }

    private void initView() {
        mChatView = (ListView) findViewById(R.id.lv_chat_list);
        mMessageEdit = (EditText) findViewById(R.id.et_chat_msg);
        mDatas.add(new ChatMessage(ChatMessage.Type.INPUT, "我是 Robot , 很高兴为您服务"));
    }

    public void sendMessage(View view) {
        final String msg = mMessageEdit.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(this, "您还没有填写信息呢...", Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage to = new ChatMessage(ChatMessage.Type.OUTPUT, msg);
        to.setDate(new Date());
        mDatas.add(to);

        mAdapter.notifyDataSetChanged();
        mChatView.setSelection(mDatas.size() - 1);
        mMessageEdit.setText("");

        // 关闭软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            // 切换开始与关闭状态
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }

        new Thread() {
            public void run() {
                ChatMessage from = null;
                try {
                    from = HttpUtil.sendMessage(msg);
                } catch (Exception e) {
                    from = new ChatMessage(ChatMessage.Type.INPUT, "服务器挂了呢...");
                }

                Message message = Message.obtain();
                message.obj = from;
                mHandler.sendMessage(message);
            }
        }.start();
    }
}
