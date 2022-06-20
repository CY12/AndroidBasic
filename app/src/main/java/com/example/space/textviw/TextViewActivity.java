package com.example.space.textviw;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.space.R;

import java.util.ArrayList;
import java.util.List;

public class TextViewActivity extends AppCompatActivity {


    private KKTextView tvSimple;
    private KKTextView tvChangeCorner;
    private KKTextView tvChangeStroke;
    private KKTextView tvChangeBackground;
    private KKTextView tvChangeBackgroundColor;
    private KKTextView tvChangeOrientation;


    private boolean isClick = false;
    private List<Integer> list = new ArrayList();
    private GradientDrawable gradientDrawable = new GradientDrawable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        tvSimple = (KKTextView) findViewById(R.id.tv_simple);
        tvChangeCorner = (KKTextView) findViewById(R.id.tv_change_corner);
        tvChangeStroke = (KKTextView) findViewById(R.id.tv_change_stroke);
        tvChangeBackground = (KKTextView) findViewById(R.id.tv_change_background);
        tvChangeBackgroundColor = (KKTextView) findViewById(R.id.tv_change_background_color);
        tvChangeOrientation = (KKTextView) findViewById(R.id.tv_change_orientation);

        tvChangeCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick){
                    tvSimple.setCorners(10);
                }else {
                    tvSimple.setCorners(30);
                }
                isClick = !isClick;
            }
        });

        tvChangeStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick){
                    tvSimple.setStroke(1, Color.parseColor("#FFE120"));
                }else {
                    tvSimple.setStroke(0,0);
                }
                isClick = !isClick;
            }
        });

        tvChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick){
                    tvSimple.setSolidColor(Color.parseColor("#FFF9D2"));
                }else {
                    tvSimple.setSolidColor(Color.parseColor("#00BCD4"));
                }
                isClick = !isClick;
            }
        });

        tvChangeBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] arrayList;
                if (isClick){
                    arrayList = new int[]{Color.parseColor("#FF6864"), Color.parseColor("#FFD466")};
                }else {
                    arrayList = new int[]{Color.parseColor("#00BCD4"), Color.parseColor("#FF6262")};
                }
                tvSimple.setColorList(arrayList);
                isClick = !isClick;
            }
        });

        tvChangeOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick){
                    tvSimple.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                }else {
                    tvSimple.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                }
                isClick = !isClick;
            }
        });
    }
}