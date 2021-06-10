package com.example.space.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


import androidx.annotation.Nullable;

public class DisView extends androidx.appcompat.widget.AppCompatTextView {
    public DisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        boolean is = super.dispatchTouchEvent(ev);
        Log.e("Test","默认 DisView dispatchTouchEvent "+is);
        return is;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean is = super.onTouchEvent(event);
        Log.e("Test","默认 DisView onTouchEvent "+is);
        return is;
    }
}
