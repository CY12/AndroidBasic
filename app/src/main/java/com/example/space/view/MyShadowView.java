package com.example.space.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyShadowView extends View {
    public MyShadowView(Context context) {
        super(context);
    }

    public MyShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        Log.e("Test","onMeasure");
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom){
        super.onLayout(changed, left, top, right, bottom);
        Log.e("Test","onLayout");
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Log.e("Test","onDraw");
        Paint mShadowPaint = new Paint();
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setAntiAlias(true);
        mShadowPaint.setColor(Color.parseColor("26D6D6D6"));
//        mShadowPaint.setShadowLayer(5, dx, dy, shadowColor);
    }
}
