package com.example.space.softinput;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.space.R;
import com.example.space.recycleview.SlideAdapter;
import com.example.space.utils.DpUtils;

public class SoftInputActivity3 extends AppCompatActivity {
    private LinearLayout title;
    private RecyclerView recyclerview;
    private EditText etSend;
    private RelativeLayout rlContent;
    private int first;
    SlideAdapter adapter;
    int lastP;
    private int rvHeight = -1;
    private int maxItemCount = -1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_input3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.blue));
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        rlContent = (RelativeLayout) findViewById(R.id.rl_content);
        title = (LinearLayout) findViewById(R.id.title);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        etSend = (EditText) findViewById(R.id.et_send);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new SlideAdapter(this);
        recyclerview.setAdapter(adapter);
        View decorView = getWindow().getDecorView();
        View contentView = findViewById(Window.ID_ANDROID_CONTENT);
//        recyclerview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                lastP = layoutManager.findLastVisibleItemPosition();
//            }
//        });
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(decorView, rlContent));
//        recyclerview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                Log.e("Test","bottom"+bottom+"oldBottom"+oldBottom+" lastP"+lastP);
//                if ( bottom < oldBottom) {
//                    recyclerview.postDelayed(new Runnable() {
//                        @Override
//
//                        public void run() {
//                            recyclerview.scrollToPosition(adapter.getItemCount()-1);
//
//                        }
//
//                    }, 100);
//
//                }
//            }
//        });
        findViewById(R.id.title).setOnClickListener(v -> {
            adapter.addItem();
            measureRecyclerView();
            recyclerview.scrollToPosition(adapter.getItemCount()-1);
//            Log.e("Test","lastP"+layoutManager.findLastVisibleItemPosition()+"  firstP"+layoutManager.findFirstVisibleItemPosition());
        });
        measureRecyclerView();
    }

    private void measureRecyclerView(){
        if (rvHeight == -1){
            recyclerview.post(new Runnable() {
                @Override
                public void run() {
                    int itemHeight = adapter.getItemCount()* DpUtils.dip2px(SoftInputActivity3.this,80);
                    rvHeight = recyclerview.getHeight();
                    Log.e("Test","itemHeight"+itemHeight+" recycleview"+recyclerview.getHeight());
                    if (itemHeight < rvHeight){
                        maxItemCount = rvHeight/DpUtils.dip2px(SoftInputActivity3.this,80);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerview.getLayoutParams();
                        layoutParams.height = itemHeight;
                        recyclerview.setLayoutParams(layoutParams);
                    }
                }
            });
        }else {
            if (maxItemCount != -1){
                if (adapter.getItemCount() <= maxItemCount){
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerview.getLayoutParams();
                    layoutParams.height = adapter.getItemCount()* DpUtils.dip2px(SoftInputActivity3.this,80);
                    recyclerview.setLayoutParams(layoutParams);
                }else {
                    recyclerview.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                    maxItemCount = -1;

                }
            }
        }


    }
    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final View contentView) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                decorView.getWindowVisibleDisplayFrame(r);

                int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
                int diff = height - r.bottom;

                if (diff >= 100) {
                    if (adapter.getItemCount() <= maxItemCount){
                        recyclerview.scrollToPosition(adapter.getItemCount()-1);
                    }
                } else {

                }
            }
        };
    }

}