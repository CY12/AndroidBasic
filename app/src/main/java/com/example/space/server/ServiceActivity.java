package com.example.space.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.space.R;

public class ServiceActivity extends AppCompatActivity {

    private TextView tvStart;
    private Chronometer chronometer;
    private boolean isRunning=false;
    private int time=60;
    private int flash=60;
    private int test=60;
    private TextView tvTime;
    private TextView tvTimeService;
    private TextView tvTop;
    private TextView tvMid;
    private TextView tvJug;
    private TextView tvBot;
    private TextView tvSup;
    private static int top1=0;
    private static int mid1=0;
    private static int jug1=0;
    private static int bot1=0;
    private static int sup1=0;




    private boolean isAlive=false;

    private Handler handler=new Handler();

    private Handler mHandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message message){
            if (top1 == 0) {
                tvTop.setVisibility(View.GONE);
            } else {
                tvTop.setText(top1 + "");
            }
            if (mid1 == 0) {
                tvMid.setVisibility(View.GONE);
            } else {
                tvMid.setText(mid1 + "");
            }
            if (jug1 == 0) {
                tvJug.setVisibility(View.GONE);
            } else {
                tvJug.setText(jug1 + "");
            }
            if (bot1 == 0) {
                tvBot.setVisibility(View.GONE);
            } else {
                tvBot.setText(bot1 + "");
            }
            if (sup1 == 0) {
                tvSup.setVisibility(View.GONE);
            } else {
                tvSup.setText(sup1 + "");
            }
        }
    };

    private TimeService timeService;

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            timeService=((TimeService.TimeBinder)iBinder).getTimeService();
            timeService.setTimeCallBack(new TimeService.TimeCallBack() {
                @Override
                public void getTime(int top, int mid, int jug, int bot, int sup) {
                    top1=top;
                    mid1=mid;
                    jug1=jug;
                    bot1=bot;
                    sup1=sup;
                    mHandler.sendEmptyMessage(0);
                }


            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private ServiceConnection c=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        tvStart = (TextView) findViewById(R.id.tv_start);
        chronometer = (Chronometer) findViewById(R.id.tv_chronometer);
        tvTime=findViewById(R.id.tv_time);
        tvTimeService=findViewById(R.id.tv_time_service);
        tvTop = (TextView) findViewById(R.id.tv_top);
        tvMid = (TextView) findViewById(R.id.tv_mid);
        tvJug = (TextView) findViewById(R.id.tv_jug);
        tvBot = (TextView) findViewById(R.id.tv_bot);
        tvSup = (TextView) findViewById(R.id.tv_sup);
        Intent intent=new Intent(ServiceActivity.this,TimeService.class);
        //intent.putExtra("time","60");
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        Intent intent1=new Intent(ServiceActivity.this,TimeService.class);
        intent1.putExtra("time",60);
        startService(intent1);
//        Intent intent1=new Intent(ServiceActivity.this,TestService.class);
//        startService(intent1);
//
//
//        startService(new Intent(ServiceActivity.this,BindService.class));
//        Intent intent2=new Intent(ServiceActivity.this,BindService.class);
//        bindService(intent2,c, Context.BIND_AUTO_CREATE);
        tvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (top1<=0){
                    startService("top",60);
                }else {

                }
            }
        });
        tvMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mid1==0){
                    startService("mid",70);
                }
            }
        });
        tvJug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jug1==0){
                    startService("jug",80);
                }
            }
        });
        tvBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bot1==0){
                    startService("bot",90);
                }
            }
        });
        tvSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sup1==0){
                    startService("sup",100);
                }
            }
        });
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRunning){
                    handler.removeCallbacks(timeCount);
                    tvStart.setText("开始");
                    chronometer.stop();
                    isRunning=!isRunning;

                    timeService.stopCount();
                }else {
                   // Log.d("Test","主线程名称:"+Thread.currentThread().getName());
                    handler.post(timeCount);
                    tvStart.setText("结束");
                    chronometer.start();
                    isRunning=!isRunning;
                    if (timeService!=null){
                        Log.d("Test","timeService startCount");
                        timeService.startCount();
                    }else {
                        Log.d("Test","timeService = null");
                    }




                }
            }
        });
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                time=time-1;
                if (time<=0){
                    chronometer.stop();
                    handler.removeCallbacks(timeCount);
                    isRunning=false;
                }
                chronometer.setText(time+"");
            }
        });
    }

    private Runnable timeCount=new Runnable() {
        @Override
        public void run() {


            if (flash>0){
                flash--;
                tvTime.setText(""+flash);
                handler.postDelayed(this,1000);
            }
        }
    };
    private void startService(String name,int time){
        Intent intent=new Intent(ServiceActivity.this,TimeService.class);
        intent.putExtra(name,time);
        startService(intent);
    }
    private void startService(String name,String reset){
        Intent intent=new Intent(ServiceActivity.this,TimeService.class);
        intent.putExtra(name,reset);
        startService(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("Test","onDestroy");
    }

    private void startTimeService(){
        if (timeService!=null){
            timeService.startCount();
        }
    }

    private void stopTimeService(){
        if (timeService!=null){
            timeService.stopCount();
        }
    }

}