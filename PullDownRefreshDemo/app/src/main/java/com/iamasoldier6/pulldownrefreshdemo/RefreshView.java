package com.iamasoldier6.pulldownrefreshdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Iamasoldier6 on 8/26/16.
 */
public class RefreshView extends LinearLayout implements View.OnTouchListener {

    public static final int STATUS_PULL_DOWN_REFRESH = 0; // 下拉状态
    public static final int STATUS_RELEASE_REFRESH = 1; // 释放立即刷新状态
    public static final int STATUS_REFRESH_ING = 2; // 正在刷新状态
    public static final int STATUS_REFRESH_FINISHED = 3; // 刷新完成或未刷新状态
    public static final int SCROLL_SPEED = -20; // 下拉时头部回滚的速度
    public static final long ONE_MINUTE = 60 * 1000; // 一分钟的毫秒值, 用于判断上次的更新时间
    public static final long ONE_HOUR = 60 * ONE_MINUTE; // 一小时的毫秒值, 用于判断上次的更新时间
    public static final long ONE_DAY = 24 * ONE_HOUR; // 一天的毫秒值, 用于判断上次的更新时间
    public static final long ONE_MONTH = 30 * ONE_DAY; // 一月的毫秒值, 用于判断上次的更新时间
    public static final long ONE_YEAR = 12 * ONE_MONTH; // 一年的毫秒值, 用于判断上次的更新时间
    private static final String UPDATED_AT = "updated_at"; // 上次更新时间的字符串常量, 用于作为 SharedPreferences 的键值
    private PullDownRefreshListener mListener; // 下拉刷新的回调接口
    private SharedPreferences preferences; // 用于存储上次更新时间
    private View header; // 下拉头的 View
    private ListView listView; // 需要去下拉刷新的 ListView
    private ProgressBar progressBar; // 刷新时显示的进度条
    private ImageView arrowImg; // 下拉和释放的箭头
    private TextView descriptionTxt; // 下拉和释放的文字描述
    private TextView updatedAtTxt; // 上次更新时间的文字描述
    private MarginLayoutParams headerLayoutParams; // 下拉头的布局参数
    private long lastUpdatedTime; // 上次更新时间的毫秒值
    private int mId = -1; // 为了防止不同界面的下拉刷新在上次更新时间上互有冲突, 使用 id 进行区分
    private int hideHeaderHeight; // 下拉头的高度
    // 当前处理的状态, 可选值有 STATUS_PULL_DOWN_REFRESH, STATUS_RELEASE_REFRESH, STATUS_REFRESH_ING
    // 和 STATUS_REFRESH_FINISHED
    private int currentStatus = STATUS_REFRESH_FINISHED;
    private int lastStatus = currentStatus; // 记录上一次的状态, 避免重复操作
    private float yDown; // 手指按下屏幕时的纵坐标
    private int touchSlop; // 在被判定为滚动之前, 用户手指可以移动的最大值
    private boolean loadOnce; // 是否已经加载过一次 layout , 这里 onLayout 中的初始化只需要加载一次
    private boolean ableToPullDown; // 当前是否可以下拉, 只有 ListView 滚动到头时, 才能下拉

    /**
     * 下拉刷新控件的构造函数, 会在运行时动态添加一个下拉头的布局
     *
     * @param context
     * @param attrs
     */
    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        header = LayoutInflater.from(context).inflate(R.layout.pull_down_refresh, null, true);
        progressBar = (ProgressBar) header.findViewById(R.id.progress_bar);
        arrowImg = (ImageView) header.findViewById(R.id.iv_arrow);
        descriptionTxt = (TextView) header.findViewById(R.id.tv_description);
        updatedAtTxt = (TextView) header.findViewById(R.id.tv_updated_at);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        refreshUpdatedAtValue();
        setOrientation(VERTICAL);
        addView(header, 0);
    }

    /**
     * 做一些关键的初始化操作, 将下拉头向上偏移进行隐藏, 给 ListView 注册 touch 事件
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !loadOnce) {
            hideHeaderHeight = -header.getHeight();
            headerLayoutParams = (MarginLayoutParams) header.getLayoutParams();
            headerLayoutParams.topMargin = hideHeaderHeight;
            listView = (ListView) getChildAt(1);
            listView.setOnTouchListener(this);
            loadOnce = true;
        }
    }

    /**
     * 当 ListView 被触摸时调用, 其中处理了多种下拉刷新的具体逻辑
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setIsAbleToPullDown(event);
        if (ableToPullDown) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    yDown = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float yMove = event.getRawY();
                    int distance = (int) (yMove - yDown);
                    // 若手指是下滑状态, 且下拉头是完全隐藏的, 则屏蔽下拉事件
                    if (distance <= 0 && headerLayoutParams.topMargin <= hideHeaderHeight) {
                        return false;
                    }
                    if (distance < touchSlop) {
                        return false;
                    }
                    if (currentStatus != STATUS_REFRESH_ING) {
                        if (headerLayoutParams.topMargin > 0) {
                            currentStatus = STATUS_RELEASE_REFRESH;
                        } else {
                            currentStatus = STATUS_PULL_DOWN_REFRESH;
                        }
                        // 通过偏移下拉头的 topMargin 值, 来实现下拉效果
                        headerLayoutParams.topMargin = (distance / 2) + hideHeaderHeight;
                        header.setLayoutParams(headerLayoutParams);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                default:
                    if (currentStatus == STATUS_RELEASE_REFRESH) {
                        // 松手时, 若是释放立即刷新状态, 则去调用正在刷新的任务
                        new RefreshTask().execute();
                    } else if (currentStatus == STATUS_PULL_DOWN_REFRESH) {
                        // 松手时, 若是下拉状态, 则去调用隐藏下拉头的任务
                        new HideHeaderTask().execute();
                    }
                    break;
            }
            // 时刻保持更新下拉头中的信息
            if (currentStatus == STATUS_PULL_DOWN_REFRESH || currentStatus == STATUS_RELEASE_REFRESH) {
                updateHeaderView();
                // 当前正处于下拉或释放状态, 要让 ListView 失去焦点, 否则被点击的那一项会一直处于选中状态
                listView.setPressed(false);
                listView.setFocusable(false);
                listView.setFocusableInTouchMode(false);
                lastStatus = currentStatus;
                // 当前正处于下拉或释放状态, 通过返回 true 屏蔽掉 ListView 的滚动事件
                return true;
            }
        }
        return false;
    }

    /**
     * 给下拉刷新控件注册一个监听器
     *
     * @param listener 监听器的实现
     * @param id       为了防止不同界面的下拉刷新在上次更新时间上互有冲突, 不同界面在注册下拉刷新监听器时一定要传入不同的 id
     */
    public void setOnRefreshListener(PullDownRefreshListener listener, int id) {
        mListener = listener;
        mId = id;
    }

    /**
     * 当所有的刷新逻辑完成后, 调用该方法, 否则 ListView 将一直处于正在刷新状态
     */
    public void finishRefresh() {
        currentStatus = STATUS_REFRESH_FINISHED;
        preferences.edit().putLong(UPDATED_AT + mId, System.currentTimeMillis()).commit();
        new HideHeaderTask().execute();
    }

    /**
     * 根据当前 ListView 的滚动状态来设定 {@link #ableToPullDown}
     * 的值, 每次都需要在 onTouch 中第一个执行, 这样可以判断出当前应该是滚动 ListView ,还是进行下拉
     *
     * @param event
     */
    private void setIsAbleToPullDown(MotionEvent event) {
        View firstChild = listView.getChildAt(0);
        if (firstChild != null) {
            int firstVisiblePos = listView.getFirstVisiblePosition();
            if (firstVisiblePos == 0 && firstChild.getTop() == 0) {
                if (!ableToPullDown) {
                    yDown = event.getRawY();
                }
                // 若首个元素的上边缘, 距离父布局值为 0 , 则说明 ListView 滚动到了最顶部, 此时允许下拉刷新
                ableToPullDown = true;
            } else {
                if (headerLayoutParams.topMargin != hideHeaderHeight) {
                    headerLayoutParams.topMargin = hideHeaderHeight;
                    header.setLayoutParams(headerLayoutParams);
                }
                ableToPullDown = false;
            }
        } else {
            // 若 ListView 中无元素, 也允许下拉刷新
            ableToPullDown = true;
        }
    }

    /**
     * 更新下拉头中的信息
     */
    private void updateHeaderView() {
        if (lastStatus != currentStatus) {
            if (currentStatus == STATUS_PULL_DOWN_REFRESH) {
                descriptionTxt.setText(getResources().getString(R.string.str_pull_down_refresh));
                arrowImg.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_RELEASE_REFRESH) {
                descriptionTxt.setText(getResources().getString(R.string.str_release_refresh));
                arrowImg.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_REFRESH_ING) {
                descriptionTxt.setText(getResources().getString(R.string.str_refresh_ing));
                progressBar.setVisibility(View.VISIBLE);
                arrowImg.clearAnimation();
                arrowImg.setVisibility(View.GONE);
            }
            refreshUpdatedAtValue();
        }
    }

    /**
     * 根据当前的状态来旋转箭头
     */
    private void rotateArrow() {
        float pivotX = arrowImg.getWidth() / 2f;
        float pivotY = arrowImg.getHeight() / 2f;
        float fromDegree = 0f;
        float toDegree = 0f;
        if (currentStatus == STATUS_PULL_DOWN_REFRESH) {
            fromDegree = 180f;
            toDegree = 360f;
        } else if (currentStatus == STATUS_RELEASE_REFRESH) {
            fromDegree = 0f;
            toDegree = 180f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegree, toDegree, pivotX, pivotY);
        animation.setDuration(100);
        animation.setFillAfter(true);
        arrowImg.startAnimation(animation);
    }

    /**
     * 刷新下拉头中上次更新时间的文字描述
     */
    private void refreshUpdatedAtValue() {
        lastUpdatedTime = preferences.getLong(UPDATED_AT + mId, -1);
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdatedTime;
        long timeIntoFormat;
        String updatedAtValue;
        if (lastUpdatedTime == -1) {
            updatedAtValue = getResources().getString(R.string.str_not_updated_yet);
        } else if (timePassed < 0) {
            updatedAtValue = getResources().getString(R.string.str_time_error);
        } else if (timePassed < ONE_MINUTE) {
            updatedAtValue = getResources().getString(R.string.str_updated_just_now);
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updatedAtValue = String.format(getResources().getString(R.string.str_updated_at), value);
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时";
            updatedAtValue = String.format(getResources().getString(R.string.str_updated_at), value);
        } else if (timePassed < ONE_MONTH) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "天";
            updatedAtValue = String.format(getResources().getString(R.string.str_updated_at), value);
        } else if (timePassed < ONE_YEAR) {
            timeIntoFormat = timePassed / ONE_MONTH;
            String value = timeIntoFormat + "个月";
            updatedAtValue = String.format(getResources().getString(R.string.str_updated_at), value);
        } else {
            timeIntoFormat = timePassed / ONE_YEAR;
            String value = timeIntoFormat + "年";
            updatedAtValue = String.format(getResources().getString(R.string.str_updated_at), value);
        }
        updatedAtTxt.setText(updatedAtValue);
    }

    /**
     * 正在刷新的任务, 此任务中会去回调注册进来的下拉刷新监听器
     */
    class RefreshTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            int topMargin = headerLayoutParams.topMargin;
            while (true) {
                topMargin = topMargin + SCROLL_SPEED;
                if (topMargin <= 0) {
                    topMargin = 0;
                    break;
                }
                publishProgress(topMargin);
                sleep(10);
            }
            currentStatus = STATUS_REFRESH_ING;
            publishProgress(0);
            if (mListener != null) {
                mListener.onRefresh();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            updateHeaderView();
            headerLayoutParams.topMargin = topMargin[0];
            header.setLayoutParams(headerLayoutParams);
        }
    }

    /**
     * 隐藏下拉头的任务, 当未进行下拉刷新或下拉刷新完成后, 此任务将会使下拉头重新隐藏
     */
    class HideHeaderTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            int topMargin = headerLayoutParams.topMargin;
            while (true) {
                topMargin = topMargin + SCROLL_SPEED;
                if (topMargin <= hideHeaderHeight) {
                    topMargin = hideHeaderHeight;
                    break;
                }
                publishProgress(topMargin);
                sleep(10);
            }
            return topMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            headerLayoutParams.topMargin = topMargin[0];
            header.setLayoutParams(headerLayoutParams);
        }

        @Override
        protected void onPostExecute(Integer topMargin) {
            headerLayoutParams.topMargin = topMargin;
            header.setLayoutParams(headerLayoutParams);
            currentStatus = STATUS_REFRESH_FINISHED;
        }
    }

    /**
     * 使当前线程睡眠指定的毫秒数
     *
     * @param time
     */
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下拉刷新的监听器, 使用下拉刷新的地方, 应注册此监听器来获取刷新回调
     */
    public interface PullDownRefreshListener {

        // 刷新时会回调此方法, 方法内编写具体的刷新逻辑, 此方法是在子线程中调用的, 不必另开线程进行耗时操作
        void onRefresh();
    }
}
