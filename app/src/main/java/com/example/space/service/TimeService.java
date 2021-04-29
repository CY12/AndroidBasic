package com.example.space.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;


import androidx.annotation.Nullable;

public class TimeService extends Service {
    private TimeBinder timeBinder=new TimeBinder();
    private int time=60;
    private int top,mid,jug,bot,sup;
    private Handler handler=new Handler();
    private TimeCallBack timeCallBack;
    private boolean isRunning=false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return timeBinder;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("Test","服务启动");
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
       //time = intent.getIntExtra("time",80);
        int tem=intent.getIntExtra("time",80);
        top=intent.getIntExtra("top",0);

        Log.d("Test","获取到的时间"+tem);
        Log.d("Test","onStartCommand   "+"获取到的时间"+time);
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(timeRunnable);
        Log.d("Test","服务销毁");
    }


    public void getTime(){
        if (timeCallBack != null) {
            Log.e("Test","timeCallBack != null");
            timeCallBack.getTime(top, mid, jug, bot, sup);
        }else {
            Log.e("Test","timeCallBack == null");
        }

    }

    public void startCount(){
        Log.d("Test","开始计时 isRunning"+!isRunning);
        control();

    }
    public void stopCount(){
        Log.d("Test","结束 isRunning"+!isRunning);
        control();

    }
    private void control(){
        if (isRunning){
            handler.removeCallbacks(timeRunnable);
            isRunning=false;
        }else {
            handler.post(timeRunnable);
            isRunning=true;
        }
    }

    private Runnable timeRunnable=new Runnable() {
        @Override
        public void run() {
            if (time>0){
                Log.d("Test","Service Thread name: "+Thread.currentThread().getName());
                time--;
                getTime();
                handler.postDelayed(this,1000);
            }else {
                isRunning=false;
            }
        }
    };

    public class TimeBinder extends Binder{
        public TimeService getTimeService(){
            return TimeService.this;
        }
    }



    public interface TimeCallBack{
         void getTime(int top,int mid,int jug,int bot,int sup);

    }
    public void setTimeCallBack(TimeCallBack timeCallBack){
        this.timeCallBack=timeCallBack;
    }
}
