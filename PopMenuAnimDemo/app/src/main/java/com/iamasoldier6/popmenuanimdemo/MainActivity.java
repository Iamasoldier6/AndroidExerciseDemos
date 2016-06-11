package com.iamasoldier6.popmenuanimdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] mRes = {R.id.dot, R.id.camera, R.id.music, R.id.location, R.id.moon};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    private boolean mFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < mRes.length; i++) {
            ImageView imageView = (ImageView) findViewById(mRes[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dot:
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
            default:
                Toast.makeText(MainActivity.this, "" + v.getId(), Toast.LENGTH_SHORT).show();
        }
    }

    private void closeAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha", 0.5F, 1F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageViews.get(1), "translationY", 200F, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageViews.get(2), "translationX", 200F, 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageViews.get(3), "translationY", -200F, 0);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageViews.get(4), "translationX", -200F, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        mFlag = true;
    }

    private void startAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha", 1F, 0.5F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageViews.get(1), "translationY", 200F);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageViews.get(2), "translationX", 200F);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageViews.get(3), "translationY", -200F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageViews.get(4), "translationX", -200F);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        mFlag = false;
    }
}
