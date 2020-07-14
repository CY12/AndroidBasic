package com.example.space.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.space.R;

import java.util.ArrayList;

public class HearBeatView extends RelativeLayout {
    private int r;//半径
    private int color;//颜色
    private int time;//时间
    private int defaultSize=100;
    private int width,height;
    private Paint paint;

    private AnimatorSet animatorSet;
    private ArrayList<Animator> animatorList;
    private int rippleAmount=5;
    private LayoutParams rippleParams;
    private ArrayList<RippleView> rippleViewList=new ArrayList<RippleView>();
    private int rippleDelay=3000/rippleAmount;
    private boolean animationRunning=false;


    public HearBeatView(Context context) {
        super(context);
    }

    public HearBeatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
    }

    private void initAttr(Context context, AttributeSet attrs){
        Log.d("Test","initAttr");
        if (attrs!=null){
            TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.HearBeatView);
            r=(int)typedArray.getDimension(R.styleable.HearBeatView_radius,dp2px(context,5));
            time=typedArray.getInteger(R.styleable.HearBeatView_time,1000);
            color=typedArray.getColor(R.styleable.HearBeatView_color, Color.GRAY);
            typedArray.recycle();

        }else {
            Log.d("Test","initAttr  attr=null");
        }
        init();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

    }
    private void init(){

        rippleParams=new LayoutParams((int)(2*r),(int)(2*r));
        rippleParams.addRule(CENTER_IN_PARENT, TRUE);

        animatorSet = new AnimatorSet();
        animatorSet.setDuration(3000);
        /**
         * AccelerateDecelerateInterpolator开始与结束的地方速率改变比较慢，在中间的时候加速
         * https://blog.csdn.net/lgaojiantong/article/details/39451243
         */
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());//插值器
        animatorList=new ArrayList<Animator>();

        for(int i=0;i<rippleAmount;i++){
            RippleView rippleView=new RippleView(getContext());
            addView(rippleView,rippleParams);
            rippleViewList.add(rippleView);
            final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleView, "ScaleX", 1.0f, 3f);
            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleXAnimator.setStartDelay(i*rippleDelay);
            animatorList.add(scaleXAnimator);
            final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleView, "ScaleY", 1.0f, 3f);
            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleYAnimator.setStartDelay(i*rippleDelay);
            animatorList.add(scaleYAnimator);
            final ObjectAnimator alphaAnimator= ObjectAnimator.ofFloat(rippleView, "Alpha", 1.0f, 0f);
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
            alphaAnimator.setStartDelay(i * rippleDelay);
            animatorList.add(alphaAnimator);
        }

        animatorSet.playTogether(animatorList);

    }

    private float dp2px(Context context, float dp) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    private class RippleView extends View{

        public RippleView(Context context) {
            super(context);
            this.setVisibility(View.INVISIBLE);
            paint=new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(color);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int radius=(Math.min(getWidth(),getHeight()))/2;
            canvas.drawCircle(radius,radius,radius,paint);
        }
    }
    public void startRippleAnimation(){
        if(!isRippleAnimationRunning()){
            for(RippleView rippleView:rippleViewList){
                rippleView.setVisibility(VISIBLE);
            }
            animatorSet.start();
            animationRunning=true;
        }
    }
    public void stopRippleAnimation(){
        if(isRippleAnimationRunning()){
            animatorSet.end();
            animationRunning=false;
        }
    }

    public boolean isRippleAnimationRunning(){
        return animationRunning;
    }


}


