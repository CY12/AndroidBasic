package com.example.space.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.space.R;

public class LiveDataActivity extends AppCompatActivity implements Observer<Lamp> {
    private ImageView ivLamp;
    private Button btChange;
    private Button btJump;
    private int time;
    private Handler handler = new Handler();
    private Button btCountTime;
    private TextView tvTime;
    private Button btStopTime;

    public MutableLiveData<Lamp> lampMutableLiveData = new MutableLiveData<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        btChange = (Button) findViewById(R.id.bt_change);
        ivLamp = (ImageView) findViewById(R.id.iv_lamp);
        btJump = (Button) findViewById(R.id.bt_Jump);
        btCountTime = (Button) findViewById(R.id.bt_countTime);
        btStopTime = (Button) findViewById(R.id.bt_stopTime);
        tvTime = (TextView) findViewById(R.id.tv_time);
        LampLiveData.getInstance().observe(this,this);



        time = 1000;
        tvTime.setText(time+" ");
        Lamp lamp = new Lamp();
        lamp.setLight(false);
        lamp.setTime(time);
        LampLiveData.getInstance().setValue(lamp);
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLight ;
                isLight = !LampLiveData.getInstance().getValue().isLight();
                Lamp lamp = new Lamp();
                lamp.setLight(isLight);
                lamp.setTime(time);
                LampLiveData.getInstance().setValue(lamp);
            }
        });

        btJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LiveDataActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        
        btCountTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(runnable,1000);
            }
        });

        btStopTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });
        // 另一种方式
        lampMutableLiveData.observe(this, new Observer<Lamp>() {
            @Override
            public void onChanged(Lamp lamp) {

            }
        });
    }

    @Override
    public void onChanged(Lamp lamp) {


        Log.d("Test","FirstActivity  change  ");
        if (lamp.isLight()){
            ivLamp.setImageResource(R.mipmap.lamp_light);
        }else {
            ivLamp.setImageResource(R.mipmap.lamp_dark);
        }
        int times = lamp.getTime();
        tvTime.setText(times+"");
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (time>0){
                time--;
                Lamp lamp = new Lamp();
                lamp.setTime(time);
                lamp.setLight(LampLiveData.getInstance().getValue().isLight());
                LampLiveData.getInstance().setValue(lamp);
                handler.postDelayed(runnable,1000);
            }

        }
    };
}