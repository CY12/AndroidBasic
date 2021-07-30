package com.example.space.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.space.R;
import com.example.space.base.BaseLifeActivity;


@Route(path = "/temp/temp2")
public class Temp2Activity extends BaseLifeActivity {

    @Override
    protected String getTAG() {
        return "Temp2Activity";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp2);
        findViewById(R.id.tv_start).setOnClickListener(v -> {

        });

    }
}