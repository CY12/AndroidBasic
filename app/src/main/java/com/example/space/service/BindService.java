package com.example.space.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BindService extends Service {
    private int time=1;
    private mBinder mBinder=new mBinder();
   private Handler handler= new Handler();
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
        Log.d("Test","Bind服务启动 onCreate ");
        handler.post(timeRunnable);
    }

    /**
     * @param intent
     * @param flags
     * @param startId
     * @return 返回参数：
     *  START_STICKY（使用空的intent 重启Service） ：
     *      如果Service所在的进程，在执行了onStartCommand方法后，被清理了，那么这个Service会被保留在已开始的状态，但是不保留传入的Intent，随后系统会尝试重新创建此Service，由于服务状态保留在已开始状态，所以创建服务后一定会调用onStartCommand方法。如果在此期间没有任何启动命令被传递到service，那么参数Intent将为null
     *  START_NOT_STICKY
     *      如果Service所在的进程，在执行了onStartCommand方法后，被清理了，则系统不会重新启动此Service。
     *  START_REDELIVER_INTENT （会使用之前的intent 在重启Service）
     *  
     *
     *
     */
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
        handler.removeCallbacks(timeRunnable);
    }


    private Runnable timeRunnable =new Runnable() {
        @Override
        public void run() {
            Log.e("BindService",time+"");
            time++;
            handler.postDelayed(this,2000);
        }
    };
}
