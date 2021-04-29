package com.example.space.jni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.space.R;

public class JNIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_n_i);
        String text = NDKTools.getStringFromNDK();
        Log.e("Test","text="+text);
        ((TextView)findViewById(R.id.tv)).setText(text);
        Handler handler = new Handler();


    }
}