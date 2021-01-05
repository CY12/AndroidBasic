package com.example.space.addview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flexibleview.FlexibleViewTools;
import com.example.flexibleview.RelativeViewPart;
import com.example.space.R;

public class AddViewActivity extends AppCompatActivity {
    private LinearLayout llLinear;
    private RelativeLayout rlRelative;
    private TextView tvAddChild;
    private TextView tvAddParams;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        Log.d("Test","onCreate AddView");
//        findViewById(R.id.rl_all).setVisibility(View.GONE);
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
                child.setText(currentTime + "");
                llLinear.addView(child);
            }
        });

        tvAddParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(AddViewActivity.this);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
                layoutParams.leftMargin = 100;
                layoutParams.topMargin = 100;
                textView.setText(System.currentTimeMillis() + "");
                rlRelative.addView(textView, layoutParams);

            }
        });
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setText("FlexibleViewTools llLinear");
        FlexibleViewTools.withLinear(textView,llLinear).setTop(100);
        TextView textView1 = new TextView(this);
        textView1.setText("FlexibleViewTools rlRelative");
        textView1.setTextSize(20);
        FlexibleViewTools.withRelative(textView1,rlRelative).setGravity(RelativeViewPart.CENTER_IN_PARENT);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("Test","onResume AddViewActivity");
    }
}