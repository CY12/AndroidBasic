package com.example.space.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.example.space.R;

public class ServiceTypeActivity extends AppCompatActivity {
    private TextView stopService;
    private TextView unbindService;
    private TextView startService;
    private TextView bindService;
    private TextView serviceActivity2;

    private TextView tvText;







    private ServiceConnection c=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("Test","bindService onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Test","bindService onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);


        startService = (TextView) findViewById(R.id.start_service);
        bindService = (TextView) findViewById(R.id.bind_service);
        stopService = (TextView) findViewById(R.id.stop_service);
        unbindService = (TextView) findViewById(R.id.unbind_service);
        serviceActivity2 = (TextView) findViewById(R.id.service_activity2);
        tvText = (TextView) findViewById(R.id.tv_text);
        if (getIntent() != null){
            String text = getIntent().getStringExtra("Foreground");
            tvText.setText(text);
        }
        Intent intent = new Intent(ServiceTypeActivity.this,BindService.class);
        startService.setOnClickListener(v -> {
            startService(intent);
        });
        bindService.setOnClickListener(v -> {
            bindService(intent,c,Context.BIND_AUTO_CREATE);
        });
        stopService.setOnClickListener(v -> {
            Log.d("Test","stopService click");
            stopService(intent);
        });
        unbindService.setOnClickListener(v -> {
            Log.d("Test","unbindService click");
            unbindService(c);
        });
        serviceActivity2.setOnClickListener(v -> {
            Intent intent1 = new Intent(this,ServiceType2Activity.class);
            startActivity(intent1);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Test","ServiceTypeActivity onDestroy");
    }
}