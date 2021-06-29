package com.example.space.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.space.MainActivity;

public abstract class BaseLifeActivity extends AppCompatActivity {
    static String TAG = "Test";

    protected abstract String getTAG();
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onCreate");
    }

    @Override
    public void onStart(){
        super.onStart();
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onStart");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onResume");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onDestroy");
    }

    @Override
    public void onPause(){
        super.onPause();
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onPause");
    }
    @Override
    public void onStop(){
        super.onStop();
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onStop");
    }

    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        if (getTAG() != null)
            Log.e(TAG,getTAG()+"onNewIntent");
    }

    public void startActivity(Class<?> cls) {
        Log.e("Test","content:"+this.toString());
        startActivity(new Intent(this, cls));
    }
}
