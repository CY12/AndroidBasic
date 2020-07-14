package com.example.space.addview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.space.R;

public class AddViewActivity extends AppCompatActivity {
    private LinearLayout llLinear;
    private RelativeLayout rlRelative;
    private TextView tvAddChild;
    private TextView tvAddParams;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        llLinear = (LinearLayout) findViewById(R.id.ll_linear);
        rlRelative = (RelativeLayout) findViewById(R.id.rl_relative);
        tvAddChild = (TextView) findViewById(R.id.tv_add_child);
        tvAddParams = (TextView) findViewById(R.id.tv_add_params);
        llLinear.setOrientation(LinearLayout.VERTICAL);


        // 获取当前的时间并转换为时间戳格式, 并设置给TextView

        tvAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView child = new TextView(AddViewActivity.this);
                child.setTextSize(20);
                child.setTextColor(getResources().getColor(R.color.colorAccent));
                long currentTime = System.currentTimeMillis();
                child.setText(currentTime+"");
                llLinear.addView(child);
            }
        });

        tvAddParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView=new TextView(AddViewActivity.this);
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(100,100);
                layoutParams.leftMargin=100;
                layoutParams.topMargin=100;
                textView.setText(System.currentTimeMillis()+"");
                rlRelative.addView(textView,layoutParams);

            }
        });
    }
}