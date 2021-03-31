package com.example.space.leak;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.space.R;
import com.example.space.base.BaseActivity;

public class LeakActivity extends BaseActivity<LeakView,LeakPresenter> implements LeakView {
    private Button tvRequest;
    private TextView tvChange;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leak;
    }

    @Override
    public LeakPresenter createPresenter() {

        Log.e("Test",this.toString());
        return new LeakPresenter(this);
    }

    @Override
    public LeakView createView() {
        return this;
    }

    @Override
    public void init() {
        tvChange = (TextView) findViewById(R.id.tv_change);
        tvRequest = (Button) findViewById(R.id.tv_request);
        tvRequest.setOnClickListener(v -> {
            Log.e("Test","setOnClickListener");
            getPresenter().toRequestData(22);
        });

    }

    private void test(String s){
        Log.e("Test","TESS == "+s);
        tvChange.setText(s);
    }

    @Override
    public void onSuccess(String s) {
        Log.e("Test","onSuccess"+s+" isFinishing"+isFinishing()+" isDestroy"+isDestroyed());
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        test(s);
    }

    @Override
    public void onFailure(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    private String TAG = "Test";

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    /**
     *开启activity
     *2021-03-30 15:04:44.438 19544-19544/com.example.space E/Test: com.example.space.leak.LeakActivity@b8fe3a6
     * 2021-03-30 15:04:44.441 19544-19544/com.example.space D/Test: onCreate
     * 2021-03-30 15:04:44.446 19544-19544/com.example.space D/Test: onStart
     * 2021-03-30 15:04:44.447 19544-19544/com.example.space D/Test: onResume
     * 2021-03-30 15:04:47.916 19544-19544/com.example.space E/Test: setOnClickListener
     * 2021-03-30 15:04:47.916 19544-19544/com.example.space E/Test: toRequestData  id == 22
     *
     * 退出activity
     * 2021-03-30 15:04:50.469 19544-19544/com.example.space D/Test: onPause
     * 2021-03-30 15:04:50.790 19544-19544/com.example.space D/Test: onStop
     * 2021-03-30 15:04:50.795 19544-19544/com.example.space D/Test: onDestroy
     * 2021-03-30 15:04:57.920 19544-19651/com.example.space E/Test: 请求结束
     * 2021-03-30 15:04:57.920 19544-19651/com.example.space E/Test: mActivity != null
     * 2021-03-30 15:04:57.921 19544-19544/com.example.space E/Test: getView() != null
     * 2021-03-30 15:04:57.921 19544-19544/com.example.space E/Test: onSuccess请求网络数据成功 name == ww isFinishingtrue isDestroytrue
     * 2021-03-30 15:04:57.948 19544-19544/com.example.space E/Test: TESS == 请求网络数据成功 name == ww
     * 2021-03-30 15:04:57.949 19544-19544/com.example.space E/Test: mContext != nullcom.example.space.leak.LeakActivity@b8fe3a6
     * 开启activity
     * 2021-03-30 15:05:20.285 19544-19544/com.example.space E/Test: com.example.space.leak.LeakActivity@c8e4923
     * 2021-03-30 15:05:20.286 19544-19544/com.example.space D/Test: onCreate
     * 2021-03-30 15:05:20.290 19544-19544/com.example.space D/Test: onStart
     * 2021-03-30 15:05:20.291 19544-19544/com.example.space D/Test: onResume
     * 2021-03-30 15:05:22.607 19544-19544/com.example.space E/Test: setOnClickListener
     * 2021-03-30 15:05:22.607 19544-19544/com.example.space E/Test: toRequestData  id == 22
     * 2021-03-30 15:05:32.616 19544-19677/com.example.space E/Test: 请求结束
     * 2021-03-30 15:05:32.617 19544-19677/com.example.space E/Test: mActivity != null
     * 2021-03-30 15:05:32.617 19544-19544/com.example.space E/Test: getView() != null
     * 2021-03-30 15:05:32.617 19544-19544/com.example.space E/Test: onSuccess请求网络数据成功 name == ww isFinishingfalse isDestroyfalse
     * 2021-03-30 15:05:32.643 19544-19544/com.example.space E/Test: TESS == 请求网络数据成功 name == ww
     * 2021-03-30 15:05:32.644 19544-19544/com.example.space E/Test: mContext != nullcom.example.space.leak.LeakActivity@c8e4923
     */


}