package com.example.space.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.space.R;

public class DisViewGroup extends RelativeLayout {
    boolean isDispatch;
    boolean isIntercept;
    public DisViewGroup(Context context) {
        super(context);
    }

    public DisViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
    }

    public DisViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        boolean is = super.dispatchTouchEvent(ev);
        Log.e("Test","默认 DisViewGroup dispatchTouchEvent "+is+"  action"+ev.getAction());
        return is;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        boolean is = super.onInterceptTouchEvent(event);
        Log.e("Test","默认 DisViewGroup onInterceptTouchEvent "+is+"  action"+event.getAction());
        return is;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean is = super.onTouchEvent(event);
        Log.e("Test","默认 DisViewGroup  onTouchEvent "+is+"  action"+event.getAction());
        return is;
    }

    private void initAttr(Context context,AttributeSet attrs){
        if (attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.DisViewGroup);
            isDispatch = typedArray.getBoolean(R.styleable.DisViewGroup_isDispatch,false);
            isIntercept = typedArray.getBoolean(R.styleable.DisViewGroup_isIntercept,false);
            typedArray.recycle();
        }
    }
}
