package com.example.space.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.space.R;

public class BroadcastActivity extends AppCompatActivity {


    private TextView tvSendImplicit;
    private TextView tvSendExplicit;
    private TextView tvAutoSend;


    private BroadcastReceiver batteryReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        tvSendImplicit = (TextView) findViewById(R.id.tv_send_implicit);
        tvSendExplicit = (TextView) findViewById(R.id.tv_send_explicit);
        tvAutoSend = (TextView) findViewById(R.id.tv_auto_send);

        tvSendImplicit.setOnClickListener(v -> {
            Log.e("Test","隐式发送");
            Intent intent  = new Intent("com.example.recriver");//隐式intent,发送隐式广播
            sendBroadcast(intent);
        });

        tvSendExplicit.setOnClickListener(v -> {
            Log.e("Test","显式发送");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BroadcastActivity.this,MyReceiver.class));//显示指定组件名称
            sendBroadcast(intent);
        });

        tvAutoSend.setOnClickListener(v -> {
            Log.e("Test","动态注册监听电量") ;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
            batteryReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    // 当前电量
                    int currentBattery = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                    // 总电量
                    int totalBattery = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                    Log.e("Test", "当前电量:" + currentBattery + "-总电量：" + totalBattery);
                }
            };
            registerReceiver(batteryReceiver, intentFilter);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (batteryReceiver != null){
            unregisterReceiver(batteryReceiver);
        }

    }
}