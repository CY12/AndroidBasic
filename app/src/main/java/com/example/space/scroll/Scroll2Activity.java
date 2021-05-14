package com.example.space.scroll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.space.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * scrollTo是绝对滚动(以view的内容的中心为原点，如果x为负值，则向右滚，y为负值向下滚)，scrollBy是相对滚动
 * 他们的相同点就是滚动的都是view中的内容，而不是view本身，view本身的getX，getY方法得到的值是不会变的
 *
 */
public class Scroll2Activity extends AppCompatActivity {
    private float mx,my;
    ValueAnimator valueAnimator;
    RecyclerView recyclerView;
    BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll2);


        final ImageView switcherView = (ImageView) this.findViewById(R.id.img);
        valueAnimator = ValueAnimator.ofInt(0,1000);

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(100000);
        showSheetDialog();
        findViewById(R.id.btn).setOnClickListener(v -> {
            Intent intent = new Intent(this,ScrollMapActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btn_dialog).setOnClickListener(v -> {
         showDialog();

//            bottomSheetDialog.show();
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                switcherView.scrollBy(1,0);



            }
        });



        valueAnimator.start();


//        switcherView.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View arg0, MotionEvent event) {
//
//                float curX, curY;
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
//                        mx = event.getX();
//                        my = event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        curX = event.getX();
//                        curY = event.getY();
//                        switcherView.scrollBy((int) (mx - curX), (int) (my - curY));
//                        mx = curX;
//                        my = curY;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        curX = event.getX();
//                        curY = event.getY();
//                        switcherView.scrollBy((int) (mx - curX), (int) (my - curY));
//                        break;
//                }
//
//                return true;
//            }
//        });

    }

    private void showDialog(){
        DragDialog dragDialog = new DragDialog(this,R.style.ReplyDialog);
        Window window = dragDialog.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        dragDialog.show();
        Display display = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams lp = dragDialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度

        dragDialog.getWindow().setAttributes(lp);
    }
List<Integer> list = new ArrayList<>();
    private void showSheetDialog() {
        for (int i=0;i<1000;i=i+30){
            list.add(i);
        }
        View view = View.inflate(this, R.layout.dualog_bottomsheet, null);
        recyclerView =  view.findViewById(R.id.dialog_recycleView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextAdapter textAdapter = new TextAdapter(R.layout.item_text,list);
        recyclerView.setAdapter(textAdapter);

        bottomSheetDialog = new BottomSheetDialog(Scroll2Activity.this, R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(view);

        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());//dialog的高度
        mDialogBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetDialog.dismiss();
                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
//        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View view, int i) {
//
//            }
//            @Override
//            public void onSlide(@NonNull View view, float v) {
//
//            }
//        });
    }

    private int getWindowHeight() {
        Resources res = Scroll2Activity.this.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }




    public void onDestroy(){
        super.onDestroy();
        valueAnimator.end();
    }
}