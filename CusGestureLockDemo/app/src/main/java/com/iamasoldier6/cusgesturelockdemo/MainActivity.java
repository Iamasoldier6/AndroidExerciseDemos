package com.iamasoldier6.cusgesturelockdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureLockViewGroup mGestureLockViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.gvg_gesture);
        mGestureLockViewGroup.setAnswer(new int[]{1, 2, 3, 4, 5});
        mGestureLockViewGroup.setOnGestureLockViewListener(
                new GestureLockViewGroup.OnGestureLockViewListener() {

                    @Override
                    public void onUnmatchedExceedBoundary() {
                        Toast.makeText(MainActivity.this, "错误 5 次...", Toast.LENGTH_SHORT).show();
                        mGestureLockViewGroup.setUnMatchExceedBoundary(5);
                    }

                    @Override
                    public void onGestureEvent(boolean matched) {
                        Toast.makeText(MainActivity.this, matched + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBlockSelected(int cId) {

                    }
                });
    }
}
