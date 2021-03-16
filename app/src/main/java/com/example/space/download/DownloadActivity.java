package com.example.space.download;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.example.space.R;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class DownloadActivity extends AppCompatActivity {
    private String path =  Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/bddownload/";
    private TextView tvPercent;
    private TextView tvStart;
    private TextView tvStop;

    long taskId;
    boolean flag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Aria.download(this).register();
        Aria.get(this).getDownloadConfig().setConvertSpeed(true);
        tvPercent = (TextView) findViewById(R.id.tv_percent);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvStop = (TextView) findViewById(R.id.tv_stop);


       File file = new File(path+"tb.apk");
        tvStart.setOnClickListener(v -> {
            if (!flag){
                Log.e("Test","download");
                taskId = Aria.download(this)
                        .load("https://download.alicdn.com/wireless/taobao4android/latest/702757.apk")     //读取下载地址
                        .setFilePath(file.getAbsolutePath()) //设置文件保存的完整路径
                        .create();   //创建并启动下载
                flag = !flag;
            }else {
                Log.e("Test","resume");
                Aria.download(this)
                        .load(taskId)//读取任务id
                        .resume();
            }
        });

        tvStop.setOnClickListener(v -> {

            Aria.download(this)
                    .load(taskId)//读取任务id
                    .stop();       // 停止任务

        });

    }
    @Download.onTaskRunning
     protected void running(DownloadTask task) {

        Log.e("Test","download url :"+task.getKey());
        int p = task.getPercent();	//任务进度百分比
        String speed = task.getConvertSpeed();	//转换单位后的下载速度，单位转换需要在配置文件中打开
//        long speed1 = task.getSpeed(); //原始byte长度速度
        tvPercent.setText("已下载"+p+"  速度："+speed);
    }

    @Download.onTaskComplete void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态
        tvPercent.setText("下载完成");
    }
}
