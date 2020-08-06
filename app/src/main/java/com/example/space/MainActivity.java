package com.example.space;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.space.addview.AddViewActivity;
import com.example.space.autoview.AutoViewActivity;
import com.example.space.base.BaseActivity;
import com.example.space.base.BaseToolbarActivity;
import com.example.space.camera.CameraActivity1;
import com.example.space.databinding.DataBindingActivity;
import com.example.space.server.ServiceActivity;
import com.example.space.thinking.observer.ObserverActivity;
import com.example.space.view.CameraFragment;
import com.example.space.view.HearBeatView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseToolbarActivity implements View.OnClickListener {
    private TextView tvAddView;
    private TextView tvDataBing;
    private TextView tvObserver;
    private TextView tvPopup;
    private TextView tvService;
    private TextView tvPlay;
    private boolean isLog=true;
    private TextView tvCamera;
    private TextView tvCamera2;
    private TextView tvCamera1;


    private MediaPlayer mediaPlayer;
    private boolean isPlaying=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tvAddView = (TextView) findViewById(R.id.tv_addView);
        tvDataBing = (TextView) findViewById(R.id.tv_dataBing);
        tvObserver = (TextView) findViewById(R.id.tv_observer);
        tvPopup = (TextView) findViewById(R.id.tv_popup);
        tvService=findViewById(R.id.tv_service);
        tvPlay=findViewById(R.id.tv_play);
        tvCamera=findViewById(R.id.tv_camera);
        tvCamera2=findViewById(R.id.tv_camera2);
        tvCamera1=findViewById(R.id.tv_camera1);
    }

    public void initData() {
        mediaPlayer=MediaPlayer.create(this,R.raw.time);

        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("123","小明");
        hashMap.put("124","小红");
        hashMap.put("125","小蓝");
        log(hashMap.toString());
        hashMap.put("123","小云");
        hashMap.put("126","小黄");
        log(hashMap.toString());
        hashMap.remove("126");
        log(hashMap.toString());
        for (Map.Entry<String,String> entry : hashMap.entrySet()){
            log("key:=="+entry.getKey()+"  value=="+entry.getValue());
        }




    }

    public void initListener() {
        tvAddView.setOnClickListener(this);
        tvDataBing.setOnClickListener(this);
        tvObserver.setOnClickListener(this);
        tvPopup.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvPlay.setOnClickListener(this);
        tvCamera.setOnClickListener(this);
        tvCamera2.setOnClickListener(this);
        tvCamera1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_addView:
                startActivity(AddViewActivity.class);
                break;
            case R.id.tv_dataBing:
                startActivity(DataBindingActivity.class);
                break;
            case R.id.tv_observer:
                startActivity(ObserverActivity.class);
                break;
            case R.id.tv_popup:
                startActivity(AutoViewActivity.class);
                break;
            case R.id.tv_service:
                startActivity(ServiceActivity.class);
                break;
            case R.id.tv_play:
                if (isPlaying){
                    mediaPlayer.pause();
                }else {
                    mediaPlayer.start();
                }
                isPlaying=!isPlaying;
                break;
            case R.id.tv_camera:
                startActivity(CameraActivity.class);
                break;
            case R.id.tv_camera2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new CameraFragment(), null)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.tv_camera1:
                startActivity(CameraActivity1.class);
                break;
            default:
                break;
        }
    }

    public void startActivity(Class<?> cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }

    public void log(String s){
        if (isLog){
            Log.d("MainActivity",s);
        }
    }
}
