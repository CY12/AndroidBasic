package com.example.space.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.space.R;

public abstract class BaseToolbarActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_toolbar);
        frameLayout = findViewById(R.id.content);
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            LayoutInflater.from(this).inflate(getLayoutId(), frameLayout, true);
        }
        initView();
        initData();

    }

    protected abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

}
