package com.example.space.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class DragView extends View {
    private int height;
    private int width;

    private int TouchSlop ;

    private int screenHeight;
    private int mLastX = 0;
    private int mLastY = 0;
    boolean isDrag = false;
    private int dragHeight;
    public DragView(Context context) {
        super(context);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        screenHeight = getHeight(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        screenHeight = getHeight(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        screenHeight = getHeight(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        if (!isDrag){
            height = MeasureSpec.getSize(heightMeasureSpec);
            width = MeasureSpec.getSize(widthMeasureSpec);

        }else {
            height = height -dragHeight;
        }

        Log.e("Test","onMeasure"+height);
        setMeasuredDimension(width,height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isDrag = true;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x - mLastX;
                int dy = y - mLastY;
                Log.e("Test","dy"+dy);
                dragHeight = dy;
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    public int getHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWm.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }
}
