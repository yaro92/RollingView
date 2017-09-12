package com.ylye.rollingview.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylye.rollingview.R;
import com.ylye.rollingview.util.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 * web版滚动公告栏
 */
public class ScrollWinAPrizeView extends FrameLayout {

    private int height;
    private int flag = 7;// 广告栏行数
    private List<String> stringList;
    private Activity activity;
    private LinearLayout linearLayoutContent;
    private ObjectAnimator animator;
    private OnClickListener listener = null;


    public ScrollWinAPrizeView(Context context) {
        super(context);
    }

    public ScrollWinAPrizeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollWinAPrizeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void initData(List<String> adList, Activity activity) {
        height = this.getMeasuredHeight();
        linearLayoutContent = new LinearLayout(activity);
        linearLayoutContent.setOrientation(LinearLayout.VERTICAL);
        //必须设置height ，否则为AT_MOST(最大模式（MeasureSpec.AT_MOST）
        //这个也就是父组件，能够给出的最大的空间，当前组件的长或宽最大只能为这么大，当然也可以比这个小。)
        linearLayoutContent.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height * 2));

        stringList = adList;
        this.activity = activity;

        for (int i = 0; i < flag + 1; i++) {
            TextView tv = new TextView(activity);
            tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height / flag));
            tv.setText(stringList.get(i));
            tv.setTag(stringList.get(i));
            if (i % 2 == 0) {
                tv.setTextColor(CommonUtils.getColor(R.color.color_grey));
            } else {
                tv.setTextColor(CommonUtils.getColor(R.color.color_orange));
            }
            tv.setGravity(Gravity.CENTER_VERTICAL);

            tv.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            });

            linearLayoutContent.addView(tv);
        }

        this.addView(linearLayoutContent);

        initAnimation();

    }

    /**
     * 加载动画
     */
    public void initAnimation() {
        animator = ObjectAnimator.ofFloat(linearLayoutContent, "translationY", 0, -height / flag);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                TextView tv = (TextView) linearLayoutContent.getChildAt(0);
                linearLayoutContent.removeView(tv);
                linearLayoutContent.addView(tv);

                if (flag + 1 >= stringList.size()) {
                    flag = 0;
                } else {
                    flag = flag + 1;
                }
                tv.setText(stringList.get(flag));
                tv.setTag(stringList.get(flag));
                tv.setGravity(Gravity.CENTER_VERTICAL);
            }
        });
    }

    /**
     * 取消动画
     */
    public void cancelAnimation() {
        animator.cancel();
    }

}
