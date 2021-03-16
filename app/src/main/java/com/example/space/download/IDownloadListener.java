package com.example.space.download;

import java.net.HttpURLConnection;

/**
 * 在这里面编写你的业务逻辑
 */
public interface IDownloadListener {
    /**
     * 取消下载
     */
    public void onCancel();

    /**
     * 下载失败
     */
    public void onFail();

    /**
     * 下载预处理,可通过HttpURLConnection获取文件长度
     */
    public void onPreDownload(HttpURLConnection connection);

    /**
     * 下载监听
     */
    public void onProgress(long currentLocation);

    /**
     * 单一线程的结束位置
     */
    public void onChildComplete(long finishLocation);

    /**
     * 开始
     */
    public void onStart(long startLocation);

    /**
     * 子程恢复下载的位置
     */
    public void onChildResume(long resumeLocation);

    /**
     * 恢复位置
     */
    public void onResume(long resumeLocation);

    /**
     * 停止
     */
    public void onStop(long stopLocation);

    /**
     * 下载完成
     */
    public void onComplete();
}