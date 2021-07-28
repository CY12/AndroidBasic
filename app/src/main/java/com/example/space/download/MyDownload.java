package com.example.space.download;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class MyDownload {
    private static volatile MyDownload myDownload;
    private String mPath =  Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/bddownload/"+"tb.apk";
    private onDownloadListener mListener;
    private String mUrl = "https://download.alicdn.com/wireless/taobao4android/latest/702757.apk";
    private ThreadPoolExecutor mThreadPool;
    private long size;
    private boolean isCancel = false;
    private boolean isStop = false;
    private int mCurrentLocation;
    private boolean isDownloading = false;
    private int mCompleteThreadNum = 0;
    public static MyDownload getMyDownload() {
//        if (myDownload == null) {
//            synchronized (MyDownload.class) {
//                if (myDownload == null) {
//                    myDownload = new MyDownload();
//                }
//            }
//        }
        myDownload = new MyDownload();
        return myDownload;
    }

    public MyDownload() {
        mThreadPool = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));
    }
    Map<Integer,DownloadPercent> map = new ConcurrentHashMap<>();
    public void setPath(String path) {
        mPath = path;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setOnDownloadListener(onDownloadListener listener) {
        mListener = listener;
    }

    public void startDownload() {
        createTask();
    }

    public void downloadTask(long start, long end,int threadId,String temPath) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //在头里面请求下载开始位置和结束位置
                    conn.setRequestProperty("Range", "bytes=" + start + "-" + end);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setConnectTimeout(80000);
                    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
                    conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
                    conn.setReadTimeout(2000);  //设置读取流的等待时间,必须设置该参数
                    InputStream is = conn.getInputStream();
                    //创建可设置位置的文件
                    RandomAccessFile file = new RandomAccessFile(mPath, "rwd");

                    //设置每条线程写入文件的位置
                    file.seek(start);
                    byte[] buffer = new byte[1024];
                    int len;
                    DownloadPercent downloadPercent = new DownloadPercent();
                    downloadPercent.setStart(start);
                    downloadPercent.setEnd(end);
                    //当前子线程的下载位置
                    long currentLocation = start;
                    Log.e("Test","开始读取 id "+threadId+" start "+start+"  end "+end);
                    while ((len = is.read(buffer)) != -1) {
                        if (isCancel) {
                            Log.d("Test", "++++++++++ thread_" + start + "_cancel ++++++++++");
                            break;
                        }

                        if (isStop) {
                            break;
                        }

                        //把下载数据数据写入文件
                        file.write(buffer, 0, len);
                        synchronized (MyDownload.this) {
                            mCurrentLocation += len;
                            float present = (float)mCurrentLocation/size;
                            mListener.onProgress(present);
                        }
                        currentLocation += len;
                        map.put(threadId,downloadPercent);
                    }
                    file.close();
                    is.close();

//            if (isCancel) {
//                synchronized (MyDownload.this) {
//                    mCancelNum++;
//                    if (mCancelNum == THREAD_NUM) {
//                        File configFile = new File(configFPath);
//                        if (configFile.exists()) {
//                            configFile.delete();
//                        }
//
//                        if (dEntity.tempFile.exists()) {
//                            dEntity.tempFile.delete();
//                        }
//                        Log.d("Test", "++++++++++++++++ onCancel +++++++++++++++++");
//                        isDownloading = false;
//                        mListener.onCancel();
//                        System.gc();
//                    }
//                }
//                return;
//            }

                    Log.e("Test", "线程【" +threadId + "】下载完毕 "+mPath);
//            writeConfig(dEntity.tempFile.getName() + "_state_" + dEntity.threadId, 1 + "");

                    mCompleteThreadNum++;
                    if (mCompleteThreadNum == 3) {
//                        File configFile = new File(mPath);
//                        if (configFile.exists()) {
//                            configFile.delete();
//                        }
                        mListener.onSuccess();
                        isDownloading = false;
                        System.gc();
                    }

                }  catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("Test","线程"+threadId+"下载失败"+e.toString()+" currentLocation");
                    isDownloading = false;
                    mListener.onFail("MalformedURLException");
                } catch (IOException e) {
                    Log.e("Test", "线程"+threadId+"下载失败【" + mUrl + "】" +e.toString());
                    isDownloading = false;
                    retry(threadId);
                    mListener.onFail("线程"+threadId+"下载失败");
                } catch (Exception e) {
                    Log.e("Test" ,"线程"+threadId+"获取流失败" + e.toString());
                    isDownloading = false;
                    mListener.onFail("获取流失败");
                }

            }
        });
    }



    public void retry(int t){

        for (Map.Entry<Integer,DownloadPercent> entry:map.entrySet()){
            if (entry.getKey() == t){
                Log.e("Test","重新尝试start "+entry.getValue().getStart()+"  end "+entry.getValue().getEnd());
                downloadTask(entry.getValue().getStart(),entry.getValue().getEnd(),t,"");
            }
        }
    }
    public void createTask() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    size = conn.getContentLength();
                    long part = size / 3;
                    long first = 0;
                    long firstEnd = part;
                    long second = part + 1;
                    long secondEnd = part * 2 + 1;
                    long third = part * 2 + 2;
                    long thirdEnd = size;
                    String temp = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/bddownload/";

                    downloadTask(first, firstEnd,1,temp+1+".tex");
                    downloadTask(second, secondEnd,2,temp+2+".tex");
                    downloadTask(third, thirdEnd,3,temp+3+".tex");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public interface onDownloadListener {
        void onSuccess();

        void onFail(String msg);

        void onProgress(float present);

        void onCancel();
    }

    private class DownloadTask implements Runnable {


        public DownloadTask(String url) {

        }

        @Override
        public void run() {

        }
    }
}
