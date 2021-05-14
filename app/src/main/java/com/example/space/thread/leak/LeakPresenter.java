package com.example.space.thread.leak;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.space.base.BasePresenter;


public class LeakPresenter extends BasePresenter<LeakView> {
    private Context mContext;
    public LeakPresenter(Context context){
        mContext = context;
    }

    public void toRequestData(int id){
        Log.e("Test","toRequestData  id == "+id);
        Activity mActivity = (Activity)mContext;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("Test","请求结束");
                if (mActivity != null){
                    Log.e("Test","mActivity != null");
                }else {
                    Log.e("Test","mActivity == null");
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getView() != null){
                            Log.e("Test","getView() != null");
                            getView().onSuccess("请求网络数据成功 name == ww");
                        }
                        if (mContext != null){
                            Log.e("Test","mContext != null"+mContext.toString());
                        }
                    }
                });
            }
        }).start();

    }
}
