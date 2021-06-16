package com.example.space.autoview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.space.R;
import com.example.space.view.PopupView;
//        2021-06-16 17:39:58.383 22738-22738/com.example.space E/Test: MyTextView  onMeasure
//        2021-06-16 17:39:58.383 22738-22738/com.example.space E/Test: MyLinearLayout  onMeasure
//        2021-06-16 17:39:58.384 22738-22738/com.example.space E/Test: MyTextView  onMeasure
//        2021-06-16 17:39:58.384 22738-22738/com.example.space E/Test: MyLinearLayout  onMeasure
//        2021-06-16 17:39:58.393 22738-22738/com.example.space E/Test: MyTextView  onMeasure
//        2021-06-16 17:39:58.393 22738-22738/com.example.space E/Test: MyLinearLayout  onMeasure
//        2021-06-16 17:39:58.394 22738-22738/com.example.space E/Test: MyTextView  onMeasure
//        2021-06-16 17:39:58.395 22738-22738/com.example.space E/Test: MyLinearLayout  onMeasure
//        2021-06-16 17:39:58.396 22738-22738/com.example.space E/Test: MyTextView  onLayout
//        2021-06-16 17:39:58.396 22738-22738/com.example.space E/Test: MyLinearLayout  onLayout
//        2021-06-16 17:39:58.413 22738-22738/com.example.space E/Test: MyLinearLayout  onMeasure
//        2021-06-16 17:39:58.413 22738-22738/com.example.space E/Test: MyLinearLayout  onLayout
//        2021-06-16 17:39:58.415 22738-22738/com.example.space E/Test: MyTextView  onDraw

// 点击开始
//        2021-06-16 17:40:58.475 22738-22738/com.example.space E/Test: MyLinearLayout  onMeasure
//        2021-06-16 17:40:58.475 22738-22738/com.example.space E/Test: MyLinearLayout  onLayout
// 再次点击开始
// 没执行方法
public class AutoViewActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private LinearLayout test;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_view);
        linearLayout = findViewById(R.id.ll_popup);

        test = findViewById(R.id.ll_test);
        findViewById(R.id.tv_start_popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
                PopupView.topPopup(linearLayout, mHeight);

                PopupView.topPopup(test, mHeight);
            }
        });

        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        // gets called after layout has been done but before display
                        // so we can get the height then hide the view

                        mHeight = linearLayout.getHeight();

                        linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        linearLayout.setVisibility(View.GONE);
                    }

                });

    }

}