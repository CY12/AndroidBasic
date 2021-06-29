package com.example.space.scroll;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.example.space.R;

public class DragDialog extends Dialog {
    private RelativeLayout rlRelative;
    private FrameLayout flFragment;
    private Context context;
    private int mLastY = 0;
    private TextView tvBack;

    public DragDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.dialog_drag);
        rlRelative = (RelativeLayout) findViewById(R.id.rl_relative);
        flFragment = (FrameLayout) findViewById(R.id.fl_fragment);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(v -> dismiss());
        dragView(rlRelative);

    }
    private void dragView(View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int y = (int) event.getRawY();

                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        int dy = y - mLastY;
                        Log.e("Test","height"+dy);
                        setHeight(view,-dy);
                        break;
                }
                mLastY = y;
                return true;
            }
        });
    }
//    public static final int WRAP_CONTENT = -2;
//    public static final int MATCH_PARENT = -1;
    private void setHeight(View view,int height){
        RelativeLayout.LayoutParams rlParams =(RelativeLayout.LayoutParams) view.getLayoutParams();//外层是
        rlParams.height = view.getHeight() + height;
//        rlParams.height =rlParams.height + height; rlParams.height 时当是wrap 和 match 是负数，得不到view的高度 所以使用view.getHeight()
        view.setLayoutParams(rlParams);
    }
}
