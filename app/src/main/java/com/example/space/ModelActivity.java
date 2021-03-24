package com.example.space;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ModelActivity extends AppCompatActivity {
    public final String TAG = "ModelActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        Log.d(TAG,"onCreate");
        findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModelActivity.this,ModelActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_start_standard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModelActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * singleTask
     * ModelActivity__MAIN: onPause -> ModelActivity: onNewIntent -> ModelActivity: onRestart -> ModelActivity: onStart -> ModelActivity: onResume -> ModelActivity__MAIN: onStop -> ModelActivity__MAIN: onDestroy
     *
     *
     * singleTop
     * ModelActivity: onPause -> ModelActivity: onNewIntent -> ModelActivity: onResume
     *
     * standard:
     * ModelActivity: onPause -> ModelActivity__MAIN: onCreate -> ModelActivity__MAIN: onStart -> ModelActivity__MAIN: onResume -> ModelActivity: onStop
     *
     *
     * startActivity流程： startActivity -> AMS 的startActivity -> 判断是否要开启新进程
     * (无 zygote 请求fork进程 创建完子进程（loop完成）会 向AMS请求 attachApplication 完成后 AMS 向子进程发送 scheduleLaunchActivity 子进程后到消息会向主线程发送LAUNCH_ACTIVITY消息 然后handle 处理消息通过反蛇创建activity 回调Activity.onCreate()等方法  )
     * 当前进程 pause完当前栈顶activity后通知AMS可以开始启动新activity了，准备就绪后AMS会通过ApplicationThread的远程接口ApplicationThreadProxy来调用scheduleLaunchActivity通知新进程开始真正启动activity的工作了，activity的onCreate和onResume函数都是在ActivityThread的handleLaunchActivity中完成的；
     *
     * https://blog.csdn.net/xgq330409675/article/details/78938926
     */
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent");
    }

}