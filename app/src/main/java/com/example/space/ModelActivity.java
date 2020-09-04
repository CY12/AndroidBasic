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
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent");
    }

}