package com.example.space.light;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;

import com.example.space.R;

public class LightPhoneActivity extends AppCompatActivity {
    private TextView tvLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_phone);
        tvLight = (TextView) findViewById(R.id.tv_light_phone);

        tvLight.setOnClickListener(v -> {
            Log.e("Test","lightPhone click");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.e("Test","睡眠10s");
                        Thread.sleep(10000);
                        lightPhone();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        });

    }
    private void lightPhone(){
        Log.e("Test","lightPhone");
        PowerManager pm = (PowerManager) this.getSystemService(this.POWER_SERVICE);
        if (!pm.isInteractive() ) {
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(
                    PowerManager.SCREEN_DIM_WAKE_LOCK
                            | PowerManager.ACQUIRE_CAUSES_WAKEUP
                            | PowerManager.ON_AFTER_RELEASE
                    , "PowerManager");
            if (wakeLock != null) {
                wakeLock.acquire();
            }

            KeyguardManager km = (KeyguardManager) getSystemService(this.KEYGUARD_SERVICE);
            //得到键盘锁管理器对象
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
            //参数是LogCat里用的Tag
            kl.disableKeyguard();
        }
    }
}