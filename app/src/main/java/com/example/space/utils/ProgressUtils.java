package com.example.space.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.space.R;

public class ProgressUtils {
    public static ObjectAnimator animator;


    public static void rotate(ImageView  view,RelativeLayout relativeLayout, int time){

        view.setImageResource(R.mipmap.loading);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(80, 80);
        layoutParams.topMargin = 200;
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        view.setLayoutParams(layoutParams);
        relativeLayout.addView(view);
        animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);

        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是旋转alpha
        // 动画效果是:0 - 360
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(time);
        animator.start();
    }

    public static void stopAnimator(RelativeLayout relativeLayout ,View view) {
        if (animator != null) {
            //animator.removeAllUpdateListeners();
            animator.end();
            relativeLayout.removeView(view);
            animator = null;
        }
    }
}
