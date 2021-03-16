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

public class ServiceType2Activity extends AppCompatActivity {

    private TextView stopService;
    private TextView unbindService;
    private TextView startService;
    private TextView bindService;

    /**
     * startService bindService 停下来 stopService 和 unbindService 必须都操作      如果bindService 绑定多个必须全部解绑在stopService
     * bindService 也是必须所有的服务都解绑才会销毁
     *
     */
    private ServiceConnection c = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("Test", "bindService onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Test", "bindService onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type2);


        startService = (TextView) findViewById(R.id.start_service);
        bindService = (TextView) findViewById(R.id.bind_service);
        stopService = (TextView) findViewById(R.id.stop_service);
        unbindService = (TextView) findViewById(R.id.unbind_service);
        Intent intent = new Intent(ServiceType2Activity.this, BindService.class);
        startService.setOnClickListener(v -> {
            startService(intent);
        });
        bindService.setOnClickListener(v -> {
            bindService(intent, c, Context.BIND_AUTO_CREATE);
        });
        stopService.setOnClickListener(v -> {
            Log.d("Test","stopService click");
            stopService(intent);
        });
        unbindService.setOnClickListener(v -> {
            Log.d("Test","unbindService click");
            unbindService(c);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Test", "ServiceTypeActivity onDestroy");
    }
}