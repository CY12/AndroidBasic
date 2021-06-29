package com.example.space.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class BottomLinearLayout extends LinearLayout {
    int maxHeight = -1;
    public BottomLinearLayout(Context context) {
        super(context);
    }

    public BottomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        Log.e("Test","BottomLinearLayout onMeasure ");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int count = getChildCount();
        Log.e("Test","BottomLinearLayout onLayout "+count);
        int curHeight = t;
        if (count == 5){
            View childTop = getChildAt(0);
            int topHeight = childTop.getMeasuredHeight();
            int topWidth = childTop.getMeasuredWidth();

            if (maxHeight < topHeight){
                maxHeight = topHeight;
            }
            View childBottom = getChildAt(1);
            int bottomHeight = childBottom.getMeasuredHeight();
            childTop.layout(l,t,r+topWidth,b+topHeight+bottomHeight);
            int width = childBottom.getMeasuredWidth();
            Log.e("Test","t:"+t+"   topHeight:"+topHeight+"  bottomHeight:"+bottomHeight+" maxHeight"+maxHeight);
            childBottom.layout(l,curHeight+maxHeight,l+width,curHeight+topHeight+bottomHeight);
        }


    }

//    public static int getHeight(Context context) {
//        DisplayMetrics dm = new DisplayMetrics();
//        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        mWm.getDefaultDisplay().getMetrics(dm);
//        int screenHeight = dm.heightPixels;
//        return screenHeight;
//    }

}
