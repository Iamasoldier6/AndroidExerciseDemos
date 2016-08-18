package com.iamasoldier6.picautoslidedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Iamasoldier6 on 8/17/16.
 */
public class PicAutoSlideView extends RelativeLayout implements View.OnTouchListener {

    public static final int SNAP_VELOCITY = 200; // 使菜单滚动, 手指滑动须达到的速度
    private int slideViewWidth; // PicAutoSlideView 的宽度
    private int curItemIndex; // 当前显示元素的下标
    private int[] borders; // 各元素的偏移边界值
    private int leftEdge = 0; // 最多可以滑动到的左边缘, 值由菜单中包含的元素总数定, marginLeft 达此值后, 不能再减少
    private int rightEdge = 0; // 最多可以滑动到的右边缘, 值恒为0, marginLeft 达此值后, 不能再增加
    private float xDown; // 记录手指按下时的横坐标
    private float xMove; // 记录手指移动时的横坐标
    private float xUp; // 记录手指抬起时的横坐标
    private LinearLayout itemsLayout; // 菜单布局
    private int itemsCount; // 菜单中包含的元素总数
    private LinearLayout dotsLayout; // 标签布局
    private View firstItem; // 菜单中的第一个元素
    private MarginLayoutParams firstItemParams; // 菜单中第一个元素的布局, 用于改变 leftMargin 的值, 决定当前该显示哪一个元素
    private VelocityTracker mVelocityTracker; // 计算手指滑动的速度
    private Handler handler = new Handler(); // 用于在定时器当中操作 UI 界面

    /**
     * 重写以下三个构造函数, 允许在 XML 中引用当前的自定义布局
     */
    public PicAutoSlideView(Context context) {
        super(context);
    }

    public PicAutoSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PicAutoSlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PicAutoSlideView);
        boolean isAutoPlay = typedArray.getBoolean(R.styleable.PicAutoSlideView_auto_play, false);
        if (isAutoPlay) {
            startAutoPlay();
        }
        typedArray.recycle();
    }

    /**
     * 滚动到下一个元素
     */
    public void scrollToNext() {
        new ScrollTask().execute(-20);
    }

    /**
     * 滚动到上一个元素
     */
    public void scrollToPrevious() {
        new ScrollTask().execute(20);
    }

    /**
     * 滚动到第一个元素
     */
    public void scrollToFirstItem() {
        new ScrollToFirstItemTask().execute(20 * itemsCount);
    }

    /**
     * onLayout() 中重新设定菜单元素和标签元素的参数
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            initItems();
            initDots();
        }
    }

    /**
     * 初始化菜单元素, 为每一个元素增添监听事件, 同时,改变所有子元素的宽度, 使等于父元素的宽度
     */
    private void initItems() {
        slideViewWidth = getWidth();
        itemsLayout = (LinearLayout) getChildAt(0);
        itemsCount = itemsLayout.getChildCount();
        borders = new int[itemsCount];
        for (int i = 0; i < itemsCount; i++) {
            borders[i] = -i * slideViewWidth;
            View item = itemsLayout.getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) item.getLayoutParams();
            params.width = slideViewWidth;
            item.setLayoutParams(params);
            item.setOnTouchListener(this);
        }
        leftEdge = borders[itemsCount - 1];
        firstItem = itemsLayout.getChildAt(0);
        firstItemParams = (MarginLayoutParams) firstItem.getLayoutParams();
    }

    /**
     * 初始化标签元素
     */
    private void initDots() {
        dotsLayout = (LinearLayout) getChildAt(1);
        refreshDotsLayout();
    }

    /**
     * 使得滚动界面变化的触控事件
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 记录手指按下时的横坐标
                xDown = event.getRawX();
                break;
            // 手指移动时, 对比按下时的横坐标, 计算出移动的距离, 来调整左侧布局的 leftMargin 值, 从而显示和
            // 隐藏布局
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                int distanceX = (int) (xMove - xDown) - (curItemIndex * slideViewWidth);
                firstItemParams.leftMargin = distanceX;
                if (beAbleToScroll()) {
                    firstItem.setLayoutParams(firstItemParams);
                }
                break;
            case MotionEvent.ACTION_UP: // 手指抬起时, 判断当前手势的意图, 从而决定是滚动到左侧布局还是右侧布局
                xUp = event.getRawX();
                if (beAbleToScroll()) {
                    if (wantScrollToPre()) {
                        if (shouldScrollToPre()) {
                            curItemIndex--;
                            scrollToPrevious();
                            refreshDotsLayout();
                        } else {
                            scrollToNext();
                        }
                    } else if (wantScrollToNext()) {
                        if (shouldScrollToNext()) {
                            curItemIndex++;
                            scrollToNext();
                            refreshDotsLayout();
                        } else {
                            scrollToPrevious();
                        }
                    }
                }
                recycleVelocityTracker();
                break;
        }
        return false;
    }

    /**
     * 当前是否能够滚动, 滚动到第一个或最后一个元素时将不能再滚动
     *
     * @return 当前 leftMargin 的值在 leftEdge 和 rightEdge 之间返回 true , 否则返回 false
     */
    private boolean beAbleToScroll() {
        return (firstItemParams.leftMargin < rightEdge) && (firstItemParams.leftMargin > leftEdge);
    }

    /**
     * 判断当前手势的意图是不是想滚动到上一个菜单元素, 如果手指移动的距离是正数, 则认为当前手势是想要滚动到上一个
     * 菜单元素
     *
     * @return 当前手势想滚动到上一个菜单元素返回 true , 否则返回 false
     */
    private boolean wantScrollToPre() {
        return xUp - xDown > 0;
    }

    /**
     * 判断当前手势的意图是不是想滚动到下一个菜单元素, 如果手指移动的距离是负数, 则认为当前手势是想要滚动到下一个
     * 菜单元素
     *
     * @return 当前手势想滚动到上一个菜单元素返回 true , 否则返回 false
     */
    private boolean wantScrollToNext() {
        return xUp - xDown < 0;
    }

    /**
     * 判断是否应该滚动到下一个菜单元素, 如果手指移动距离大于屏幕的 1/2 , 或者手指移动速度大于 SNAP_VELOCITY ,
     * 就认为应该滚动到下一个菜单元素
     *
     * @return 如果应该滚动到下一个菜单元素返回 true , 否则返回 false
     */
    private boolean shouldScrollToNext() {
        return xDown - xUp > slideViewWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
    }

    /**
     * 判断是否应该滚动到上一个菜单元素, 如果手指移动距离大于屏幕的 1/2 , 或者手指移动速度大于 SNAP_VELOCITY ,
     * 就认为应该滚动到上一个菜单元素
     *
     * @return
     */
    private boolean shouldScrollToPre() {
        return xUp - xDown > slideViewWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
    }

    /**
     * 刷新标签元素布局, 每次 curItemIndex 值改变的时候都应该进行刷新
     */
    private void refreshDotsLayout() {
        dotsLayout.removeAllViews();
        for (int i = 0; i < itemsCount; i++) {
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
            linearParams.weight = 1;
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            ImageView image = new ImageView(getContext());
            if (i == curItemIndex) {
                image.setBackgroundResource(R.drawable.dot_selected);
            } else {
                image.setBackgroundResource(R.drawable.dot_unselected);
            }
            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            relativeLayout.addView(image, relativeParams);
            dotsLayout.addView(relativeLayout, linearParams);
        }
    }

    /**
     * 创建 VelocityTracker 对象, 并将触摸事件加入到 VelocityTracker 中
     *
     * @param event 右侧布局监听控件的滑动事件
     */
    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 获取手指在右侧布局的监听 View 上的滑动速度
     *
     * @return 滑动速度, 以每秒移动的像素值为单位
     */
    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }

    /**
     * 回收 VelocityTracker 对象
     */
    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    /**
     * 检测菜单滚动时, 是否有穿越 border , border 的值都存储在 {@link #borders} 中
     *
     * @param leftMargin 第一个元素的左偏移值
     * @param speed      滚动的速度, 正数说明向右滚动, 负数说明向左滚动
     * @return 穿越任何一个 border 返回 true , 否则返回 false
     */
    private boolean isCrossBorder(int leftMargin, int speed) {
        for (int border : borders) {
            if (speed > 0) {
                if (leftMargin >= border && leftMargin - speed < border) {
                    return true;
                }
            } else {
                if (leftMargin <= border && leftMargin - speed > border) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 找到离当前的 leftMargin 最近的一个 border 值
     *
     * @param leftMargin 第一个元素的左偏移值
     * @return 离当前的 leftMargin 最近的一个 border 值
     */
    private int findClosestBorder(int leftMargin) {
        int absLeftMargin = Math.abs(leftMargin);
        int closestBorder = borders[0];
        int closestMargin = Math.abs(Math.abs(closestBorder) - absLeftMargin);
        for (int border : borders) {
            int margin = Math.abs(Math.abs(border) - absLeftMargin);
            if (margin < closestMargin) {
                closestBorder = border;
                closestMargin = margin;
            }
        }
        return closestBorder;
    }

    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... speed) {
            int leftMargin = firstItemParams.leftMargin;
            // 根据传入的速度来滚动界面, 当滚动穿越 border 时, 跳出循环
            while (true) {
                leftMargin = leftMargin + speed[0];
                if (isCrossBorder(leftMargin, speed[0])) {
                    leftMargin = findClosestBorder(leftMargin);
                    break;
                }
                publishProgress(leftMargin);
                // 为了要有滚动效果产生, 每次循环使线程睡眠 10 毫秒, 使得肉眼能够看到滚动动画
                sleep(10);
            }
            return leftMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... leftMargin) {
            firstItemParams.leftMargin = leftMargin[0];
            firstItem.setLayoutParams(firstItemParams);
        }

        @Override
        protected void onPostExecute(Integer leftMargin) {
            firstItemParams.leftMargin = leftMargin;
            firstItem.setLayoutParams(firstItemParams);
        }
    }

    /**
     * 使当前线程睡眠指定的毫秒数
     *
     * @param millis
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ScrollToFirstItemTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... speed) {
            int leftMargin = firstItemParams.leftMargin;
            while (true) {
                leftMargin = leftMargin + speed[0];
                // 当 leftMargin 大于 0 时, 说明已经滚动到了第一个元素, 跳出循环
                if (leftMargin > 0) {
                    leftMargin = 0;
                    break;
                }
                publishProgress(leftMargin);
                sleep(20);
            }
            return leftMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... leftMargin) {
            firstItemParams.leftMargin = leftMargin[0];
            firstItem.setLayoutParams(firstItemParams);
        }

        @Override
        protected void onPostExecute(Integer leftMargin) {
            firstItemParams.leftMargin = leftMargin;
            firstItem.setLayoutParams(firstItemParams);
        }
    }

    /**
     * 开启图片自动播放功能, 当滚动到最后一张图片的时候, 会自动回滚到第一张图片
     */
    public void startAutoPlay() {
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (curItemIndex == itemsCount - 1) {
                    curItemIndex = 0;
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            scrollToFirstItem();
                            refreshDotsLayout();
                        }
                    });
                } else {
                    curItemIndex++;
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            scrollToNext();
                            refreshDotsLayout();
                        }
                    });
                }
            }
        }, 3000, 3000);
    }


}
