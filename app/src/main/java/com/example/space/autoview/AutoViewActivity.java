package com.example.space.autoview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.space.R;
import com.example.space.view.PopupView;

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
                PopupView.topPopup(linearLayout,mHeight);

                PopupView.topPopup(test,mHeight);
            }
        });
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener(){

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