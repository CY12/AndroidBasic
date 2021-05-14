package com.example.space.scroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.flexibleview.FlexibleViewTools;
import com.example.space.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollMapActivity extends AppCompatActivity {
    private RelativeLayout scrollView;
    private RelativeLayout rlRelative;
    private int screenHeight;
    private int mLastX = 0;
    private int mLastY = 0;
    private RecyclerView recyclerview;
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_map);
        scrollView = (RelativeLayout) findViewById(R.id.scrollView);
        rlRelative = (RelativeLayout) findViewById(R.id.rl_relative);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        for (int i=0;i<1000;i=i+30){
            list.add(i);
        }
        TextAdapter textAdapter = new TextAdapter(R.layout.item_text,list);
        recyclerview.setAdapter(textAdapter);
        screenHeight = getHeight(this);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int y = (int) event.getRawY();

                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        int dy = y - mLastY;
                        Log.e("Test","height"+dy);
                        setHeight(scrollView,-dy);
                        break;
                }
                mLastY = y;
                return true;
            }
        });
    }
    private void setHeight(View view,int height){
        RelativeLayout.LayoutParams rlParams =(RelativeLayout.LayoutParams) view.getLayoutParams();
        rlParams.height =rlParams.height + height;
        view.setLayoutParams(rlParams);
    }


    public int getHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWm.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }
}