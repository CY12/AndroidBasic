package com.example.space.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BindService extends Service {
    private int time=80;
    private mBinder mBinder=new mBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Test","onBind");
        return mBinder;
    }
    public class  mBinder extends Binder {
        public void logTime(){
            Log.d("Test","logTime:"+time);
        }
    }
    public void onCreate(){
        super.onCreate();
        Log.d("Test","Bind服务启动");
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        //time = intent.getIntExtra("time",80);
        //Log.d("Test","获取到的时间"+time);
        Log.d("Test","Bind onStartCommand");
        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Test", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("Test", "onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("Test","Bind服务销毁");
    }

}
