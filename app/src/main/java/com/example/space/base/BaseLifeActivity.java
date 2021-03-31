package com.example.space.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseLifeActivity extends AppCompatActivity {
    static String TAG = "Test";

    protected abstract String getTAG();
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Log.e(TAG,getTAG()+"onCreate");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.e(TAG,getTAG()+"onStart");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.e(TAG,getTAG()+"onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e(TAG,getTAG()+"onResume");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e(TAG,getTAG()+"onDestroy");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.e(TAG,getTAG()+"onPause");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.e(TAG,getTAG()+"onStop");
    }

    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.e(TAG,getTAG()+"onNewIntent");
    }
}
