package com.example.space.service;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.ImageView;

import com.example.space.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * HandlerThread本质上是一个线程类，它继承了Thread；
 * HandlerThread有自己的内部Looper对象，可以进行looper循环；
 * 通过获取HandlerThread的looper对象传递给Handler对象，可以在handleMessage方法中执行异步任务。
 * 创建HandlerThread后必须先调用HandlerThread.start()方法，Thread会先调用run方法，创建Looper对象。
 *
 */
public class HandlerThreadActivity extends Activity {
    /**
     * 图片地址集合
     */
    private String url[]={
            "https://img-blog.csdn.net/20160903083245762",
            "https://img-blog.csdn.net/20160903083252184",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083311972",
            "https://img-blog.csdn.net/20160903083319668",
            "https://img-blog.csdn.net/20160903083326871"
    };
    private ImageView imageView;
    private Handler mUIHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            LogUtils.e("次数:"+msg.what);
//            ImageModel model = (ImageModel) msg.obj;
//            imageView.setImageBitmap(model.bitmap);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
//        imageView= (ImageView) findViewById(R.id.image);
        //创建异步HandlerThread
        HandlerThread handlerThread = new HandlerThread("downloadImage");
        //必须先开启线程
        handlerThread.start();
        //子线程Handler
        Handler childHandler = new Handler(handlerThread.getLooper(),new ChildCallback());
        for(int i=0;i<7;i++){
            //每个1秒去更新图片
            childHandler.sendEmptyMessageDelayed(i,1000*i);
        }
    }
    /**
     * 该callback运行于子线程
     */
    class ChildCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            //在子线程中进行网络请求
            Bitmap bitmap=downloadUrlBitmap(url[msg.what]);
//            ImageModel imageModel=new ImageModel();
//            imageModel.bitmap=bitmap;
//            imageModel.url=url[msg.what];
            Message msg1 = new Message();
            msg1.what = msg.what;
//            msg1.obj =imageModel;
            //通知主线程去更新UI
            mUIHandler.sendMessage(msg1);
            return false;
        }
    }
    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap=null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap=BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}