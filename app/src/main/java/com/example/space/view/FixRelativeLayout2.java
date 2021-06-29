package com.example.space.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class FixRelativeLayout2 extends RelativeLayout {

    int nowHeight = -1;
    int oldHeight = -1;
    int bottomParams = -1;

    public FixRelativeLayout2(Context context) {
        super(context);
    }

    public FixRelativeLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixRelativeLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        nowHeight = h;
        oldHeight = oldh;
        Log.e("Test", "Layout2  onSizeChanged" + h + "  oldh" + oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("Test", "Layout2  onLayout");
        View child = getChildAt(1);
        int height = child.getMeasuredHeight();
        int width = child.getMeasuredWidth();
        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        if (bottomParams == -1) {
            bottomParams = layoutParams.bottomMargin;
            Log.e("Test", "bottomParams" + bottomParams);
        }

        if (nowHeight < oldHeight) {
            layoutParams.bottomMargin = bottomParams;
        } else if (nowHeight > oldHeight) {
            layoutParams.bottomMargin = 0;

        }

        Log.e("Test", "height " + height + " width " + width + " t" + t + " nowHeight" + nowHeight + " oldHeight" + oldHeight + " layoutParams.bottomMargin" + layoutParams.bottomMargin);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("Test", "Layout2 onDraw");
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e("Test", "Layout2 dispatchDraw");
    }
}
