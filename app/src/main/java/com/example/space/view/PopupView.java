package com.example.space.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class PopupView {
    public static void topPopup(LinearLayout linearLayout,int Height) {


        float curTranslationY = linearLayout.getTranslationY();
        int height = linearLayout.getHeight();
        if (Height==0){
            Log.d("Test","height==0");
            height=Height;}

        Log.d("Test","curTranslationY:"+curTranslationY+  "  height"+height);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(1000);
        ObjectAnimator animator=ObjectAnimator.ofFloat(linearLayout,"translationY",curTranslationY-height,curTranslationY);
        ObjectAnimator alpha=ObjectAnimator.ofFloat(linearLayout,"alpha",0f,1f);
        animatorSet.play(animator).with(alpha);
        animatorSet.start();
    }
}

