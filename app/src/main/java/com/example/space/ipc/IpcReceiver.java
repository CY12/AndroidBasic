package com.example.space.ipc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class IpcReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Test","IpcReceiver收到广播 ");
        try {
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                String msg = bundle.getString("name")+bundle.getString("msg");
                Log.e("Test","IpcReceiver收到广播 "+msg);
            }else {
                Log.e("Test","budle 空");
            }


        }catch (Exception e){
            Log.e("Test",e.toString());
        }

        if ("net.ipc.BROADCAST".equals(intent.getAction())){
            Log.e("Test","action 匹配");
        }else {
            Log.e("Test","action"+intent.getAction());
        }
    }
}
