package com.example.space.view.wave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.space.R;

public class WaveProgressActivity extends AppCompatActivity {
    WaveProgressView waveProgressView;
    TextView tvProgress;
    mWaveView mWaveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_progress);
        waveProgressView = findViewById(R.id.wave_progress);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        mWaveView = findViewById(R.id.mWaveView);


        tvProgress.setOnClickListener(v -> mWaveView.startAnimator(3000, 0.80f));


//        mWaveView.startAnimator();
//        waveProgressView.setDrawSecondWave(true);
//        waveProgressView.setTextView(tvProgress);
//        waveProgressView.setOnAnimationListener(new WaveProgressView.OnAnimationListener() {
//            @Override
//            public String howToChangeText(float interpolatedTime, float updateNum, float maxNum) {
//                DecimalFormat decimalFormat=new DecimalFormat("0.00");
//                String s = decimalFormat.format(interpolatedTime * updateNum / maxNum * 100)+"%";
//                return s;
//            }
//
//            @Override
//            public float howToChangeWaveHeight(float percent, float waveHeight) {
//                return (1-percent)*waveHeight;
//            }
//        });
//
//
//        waveProgressView.setProgressNum(80,4000);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mWaveView.stopAnimator();
    }
}