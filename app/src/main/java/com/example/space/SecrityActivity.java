package com.example.space;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecrityActivity extends AppCompatActivity {

    private EditText etInput;
    private TextView tvConfirm;
    private TextView tvPwd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secrity);
        etInput = (EditText) findViewById(R.id.et_input);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        tvPwd = findViewById(R.id.tv_pwd);
        // 这是MainGitTest 3
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        tvConfirm.setOnClickListener(v -> {
            if (etInput.getText().toString().length()<3){
                Toast.makeText(SecrityActivity.this,"error",Toast.LENGTH_SHORT).show();
                return;
            }
            String s = etInput.getText().toString();
            int a =  Integer.parseInt(s.substring(0,1));
            int b =  Integer.parseInt(s.substring(1,2));
            int c =  Integer.parseInt(s.substring(2,3));
            Log.d("Test","a"+a+" b"+b+" c"+c);
            String pwd = getPwd(a,b,c);
            tvPwd.setText(pwd+"");


        });
    }

    private String getPwd(int first,int second,int third){
        int []j = new int [] {first,second,third};
        int a = ((int) Math.pow(j[0],j[0]))+j[0];
        int b = ((int)Math.pow(j[1], j[1]))+j[1];
        int c = ((int) Math.pow(j[2], j[2]))+j[2];
        Log.d("Test","a"+a+" b"+b+" c"+c);
        String d = a+"";
        String e1 = b+"";
        String f = c+"";
        if(d.length()>=2) {
            d = d.substring(0,2);
        }
        if(e1.length()>=2) {
            e1 = e1.substring(0,2);
        }
        if(f.length()>=2) {
            f = f.substring(0,2);
        }
        String pwd = d+e1+f;
        if(pwd.length()>6) {
            pwd = pwd.substring(0,7);
        }else if(pwd.length()>2){
            String left = "";
            int g = 6 - pwd.length();
            for(int i = 0;i<g;i++) {
                String s = j[i]+"";
                left = left + s.substring(0,1);
            }
            pwd = pwd +left;

        }
        return pwd;
    }
}