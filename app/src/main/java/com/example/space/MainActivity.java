package com.example.space;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.space.addview.AddViewActivity;
import com.example.space.autoview.AutoViewActivity;
import com.example.space.base.BaseActivity;
import com.example.space.databinding.DataBindingActivity;
import com.example.space.thinking.observer.ObserverActivity;
import com.example.space.view.HearBeatView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvAddView;
    private TextView tvDataBing;
    private TextView tvObserver;
    private TextView tvPopup;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }


    public void initView() {
        tvAddView = (TextView) findViewById(R.id.tv_addView);
        tvDataBing = (TextView) findViewById(R.id.tv_dataBing);
        tvObserver = (TextView) findViewById(R.id.tv_observer);
        tvPopup = (TextView) findViewById(R.id.tv_popup);
    }

    public void initData() {


    }

    public void initListener() {
        tvAddView.setOnClickListener(this);
        tvDataBing.setOnClickListener(this);
        tvObserver.setOnClickListener(this);
        tvPopup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_addView:
                startActivity(AddViewActivity.class);
                break;
            case R.id.tv_dataBing:
                startActivity(DataBindingActivity.class);
                break;
            case R.id.tv_observer:
                startActivity(ObserverActivity.class);
                break;
            case R.id.tv_popup:
                startActivity(AutoViewActivity.class);
                break;
            default:
                break;
        }
    }

    public void startActivity(Class<?> cls){
        startActivity(new Intent(MainActivity.this,cls));
    }
}
