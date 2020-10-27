package com.example.space.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.space.R;

public class LoadingDialog extends Dialog {

    private ImageView ivLoading;
    private TextView tvLoading;
    private int time;
    private ObjectAnimator animator;

    public LoadingDialog(@NonNull Context context, int themeResId,int time) {
        super(context, themeResId);
        this.time = time;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ivLoading = (ImageView) findViewById(R.id.iv_loading);
        tvLoading = (TextView) findViewById(R.id.tv_loading);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        animator =  ObjectAnimator.ofFloat(ivLoading,"rotation", 0f, 360f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(time);
        animator.start();

    }
    @Override
    public void dismiss(){
        if (animator != null){
            animator.end();
        }
    }
}
