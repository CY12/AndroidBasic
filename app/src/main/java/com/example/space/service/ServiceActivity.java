package com.example.space.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
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
import com.example.space.SecrityActivity;

/**
 *  startService: onCreate() -> onStartCommand() ->onDestroy
 *  多次启动 只会执行 onStartCommand
 *  当activity 退出后 service 仍然在后台运行
 *  当应用程序退出时 service 退出 不执行onDestroy
 *
 *  bindService: onCreate() -> onBind() -> onUnbind() -> onDestroy()
 *  多次bind 只有第一次会执行 onCreate() -> onBind() ，其他bind 不会执行任何操作。第一次执行已经得到  IBinder实例 ，
 *  并且该IBinder实例在所有的client之间是共享的，所以其他bind 时 直接获取上次已经获取到的IBinder实例，并将其参数传入ServiceConnection
 *  且其中activity unbind 操作 service 不会中止，无生命周期变化，只当最后一个 unbind 才会执行 onUnbind() -> onDestroy()
 *  当只有一个activity 退出时 service 中止
 *
 *  startService bindService 混合使用
 *  要终止Service，需要unbindService和stopService都调用才行。与两者顺序无关。
 *
 *
 *
 */
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
    private TextView tvForeground;





    private boolean isAlive=false;

    private Handler handler=new Handler();

    private Handler mHandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message message){
            Log.e("Test","ServiceActivity handleMessage top1"+top1+"  tvTop"+tvTop);
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
                    Log.e("Test","ServiceActivity getTime");
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
            Log.e("Test","bindService onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("Test","bindService onServiceDisconnected");
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
        tvForeground = (TextView) findViewById(R.id.tv_foreground);

        Intent intent=new Intent(ServiceActivity.this,TimeService.class);
        //intent.putExtra("time","60");
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        Intent intent1=new Intent(ServiceActivity.this,TimeService.class);
        intent1.putExtra("time",60);
        startService(intent1);
        tvForeground.setOnClickListener(v -> {
            Intent intent2 = new Intent(this,ForegroundService.class);
            if (Build.VERSION.SDK_INT >= 26){
                startForegroundService(intent2);
            }else {
                startService(intent2);
            }


        });

//        Intent intent3 = new Intent(ServiceActivity.this,BindService.class);
//        startService(intent3);
//        Intent intent2=new Intent(ServiceActivity.this,BindService.class);
//        bindService(intent2,c,Context.BIND_AUTO_CREATE);
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
        findViewById(R.id.tv_start_type).setOnClickListener(v -> {
            Intent intent4 = new Intent(this,ServiceTypeActivity.class);
            startActivity(intent4);
        });
        findViewById(R.id.unBindService).setOnClickListener(v -> {
            unbindService(c);
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