package com.example.space.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public static String myReceiver = "myReceiver";
    private String msg;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null){
            msg = intent.getExtras().getString(myReceiver);
        }
        Toast.makeText(context,context.toString()+" msg: "+msg,Toast.LENGTH_SHORT).show();
    }
}
