package com.iamasoldier6.rxjavaretrofitdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.iamasoldier6.rxjavaretrofitdemo.R;
import com.iamasoldier6.rxjavaretrofitdemo.entity.Subject;
import com.iamasoldier6.rxjavaretrofitdemo.http.HttpMethods;
import com.iamasoldier6.rxjavaretrofitdemo.subscribers.ProgressSubscriber;
import com.iamasoldier6.rxjavaretrofitdemo.subscribers.SubscriberOnNextListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.tv_result)
    TextView mTvResult;
    @Bind(R.id.btn_click_me)
    Button mBtnClickMe;

    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {

            @Override
            public void onNext(List<Subject> subjects) {
                mTvResult.setText(subjects.toString());
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @OnClick(R.id.btn_click_me)
    public void onClick() {
        getMovie();
    }

    // 网络请求
    private void getMovie() {

        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, MainActivity.this),
                0, 10);
    }
}
