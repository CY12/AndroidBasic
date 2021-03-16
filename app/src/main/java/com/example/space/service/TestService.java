package com.example.space.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {

    private int time = 1;
    private Handler handler = new Handler();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        super.onCreate();
        Log.d("Test","Test服务启动");
        handler.post(timeRunnable);
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        //time = intent.getIntExtra("time",80);
        //Log.d("Test","获取到的时间"+time);

        Log.d("Test","TestonStartCommand");
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("Test","Test服务销毁");
    }
    private Runnable timeRunnable =new Runnable() {
        @Override
        public void run() {
            Log.e("StartService",time+"");
            time++;
            handler.postDelayed(this,2000);
        }
    };

}
