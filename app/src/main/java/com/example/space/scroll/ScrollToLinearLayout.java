package com.example.space.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class ScrollToLinearLayout extends LinearLayout {

    private final String TAG = getClass().getName();
    private int mLastX = 0;
    private int mLastY = 0;

    public ScrollToLinearLayout(Context context) {
        super(context);
    }

    public ScrollToLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollToLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x - mLastX;
                int dy = y - mLastY;
                Log.e(TAG, "ScrollX = " + getScrollX() + "   ScrollY = " + getScrollY());
                scrollTo(dx, dy);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }
}
