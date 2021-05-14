package com.example.space.life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.space.R;

/**
 * Intent Flag组合
 * FLAG_ACTIVITY_NEW_TASK和FLAG_ACITIVTY_CLEAR_TOP
 * 效果：如果栈中存在该Activity，那么则将要跳转的Activity及其之上的Actviity全部清除，然后将创建要跳转的Activity放入栈中。因为跳转的Activity是重新创建的，所以不会走onNewIntent方法。
 * 2021-05-10 14:55:51.393 15947-15947/com.example.space D/ModelActivity: onDestroy
 * 2021-05-10 14:55:51.419 15947-15947/com.example.space D/ModelActivity: onCreate
 * 2021-05-10 14:55:51.421 15947-15947/com.example.space D/ModelActivity: onStart
 * 2021-05-10 14:55:51.423 15947-15947/com.example.space D/ModelActivity: onResume
 * FLAG_ACTIVITY_NEW_TASK和FLAG_ACITIVTY_CLEAR_TASK
 * 效果：跳转Activity所在的任务栈中所有Activity全部清除，然后创建要跳转的Activity并放入栈中。
 *
 * FLAG_ACTIVITY_SINGLE_TOP和FLAG_ACTIVITY_CLEAR_TOP
 * 效果：如果跳转Activity所在的任务栈中存在该Activity，那么将该Activity以上的Activity全部清除，然后调用跳转Activity的onNewIntent()方法。
 * 这种组合方式等同于singleTask的launchMode模式。
 * 2021-05-10 14:55:34.127 15947-15947/com.example.space D/ModelActivity: onRestart
 * 2021-05-10 14:55:34.128 15947-15947/com.example.space D/ModelActivity: onStart
 * 2021-05-10 14:55:34.129 15947-15947/com.example.space D/ModelActivity: onNewIntent
 * 2021-05-10 14:55:34.129 15947-15947/com.example.space D/ModelActivity: onResume
 */
public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        findViewById(R.id.tv_first).setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this,ModelActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        findViewById(R.id.tv_second).setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this,ModelActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }

}
