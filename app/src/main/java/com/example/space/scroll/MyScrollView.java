package com.example.space.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class MyScrollView extends NestedScrollView {
    private int TouchSlop;
    public MyScrollView(@NonNull Context context) {
        super(context);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    private float downY;
    private int dragHeight;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (this.isEnabled()) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.e("Test","ACTION_DOWN event.getY()"+event.getRawY());
                    Log.e("Test","==============");
                    downY = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("Test","event.getY()"+event.getRawY());
                    dragHeight =(int)(event.getRawY()-downY);
                    if (Math.abs(dragHeight)> TouchSlop){
                        Log.e("Test","dragHeight"+dragHeight);
                        scrollTo(0,(int)event.getRawY());
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;

            }

            return true;
        }
        return false;
    }

}
