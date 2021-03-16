package com.example.space.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class AppReceiver extends BroadcastReceiver {

    private final String TAG = "Test";

    public AppReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //判断广播类型
        String action = intent.getAction();
        //获取包名
        Uri appName = intent.getData();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            Log.e(TAG, "安装了：" + appName);
        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
            Log.e(TAG, "更新了：" + appName);
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Log.e(TAG, "卸载了：" + appName);
        }
    }
}
