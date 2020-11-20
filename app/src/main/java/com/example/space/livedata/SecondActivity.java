package com.example.space.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.space.R;

public class SecondActivity extends AppCompatActivity implements Observer<Lamp> {

    private Button btChange;
    private ImageView ivLamp;
    private Button btCountTime;
    private TextView tvTime;
    private Button btStopTime;
    private Handler handler = new Handler();
    private int time;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btChange = (Button) findViewById(R.id.bt_change);
        ivLamp = (ImageView) findViewById(R.id.iv_lamp);
        btCountTime = (Button) findViewById(R.id.bt_countTime);
        tvTime = (TextView) findViewById(R.id.tv_time);
        btStopTime = (Button) findViewById(R.id.bt_stopTime);
        LampLiveData.getInstance().observe(this,this);
        time = LampLiveData.getInstance().getValue().getTime();
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
    }

    /**
     * 当这个lamp 改变时返回上一个是会触发 onChanged
     * @param lamp
     */
    @Override
    public void onChanged(Lamp lamp) {
        Log.d("Test","SecondActivity  change  ");
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