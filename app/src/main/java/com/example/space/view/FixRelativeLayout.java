package com.example.space.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FixRelativeLayout extends RelativeLayout {
    private int mScreenHeight;
    public FixRelativeLayout(Context context) {
        super(context);
    }

    public FixRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenHeight = getHeight(getContext());
    }

    public FixRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenHeight = getHeight(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mScreenHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("Test","onMeasure");
    }
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        Log.e("Test","onLayout");
//        View child = getChildAt(1);
//        int height = child.getMeasuredHeight();
//        int width = child.getMeasuredWidth();
//        int top = mScreenHeight+height;
//        Log.e("Test","mScreenHeight"+mScreenHeight+" height"+height);
//        child.layout(l,top/2,r+width,top/2+height);
//    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("Test","onSizeChanged");
    }
    
    public  int getHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWm.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }

}
