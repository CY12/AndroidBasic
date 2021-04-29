package com.example.space.leak;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.space.R;
import com.example.space.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * 四种引用 和 引用队列
 *1.强引用
 *  正常的引用，生命周期最长，例如 Object obj = new Object(); 当JVM内存不足时，宁可抛出OutOfMemoryError，也不愿回收存活着强引用的对象。
 *  使用obj = null; 可以去除强引用
 *2.软引用（SoftReference）
 *  当JVM内存不足时会先回收软引用指向的对象，即抛出OutOfMemoryError之前，会去清理软引用对象。
 *3.弱引用（WeakReference）
 *   JVM内存回收时，不论是否内存不足，都会回收弱引用的对象
 *4.虚引用（PhantomReference）
 *  虚引用和前面的软引用、弱引用不同，它并不影响对象的生命周期。在java中用java.lang.ref.PhantomReference类表示。如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收。
 *  设置虚引用的目的是为了被虚引用关联的对象在被垃圾回收器回收时，能够收到一个系统通知。虚引用必须和引用队列关联使用，当垃圾回收器准备回收一个对象时,如果发现该对象具有虚引用，那么在回收之前会首先把该对象的虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是否已经加入虚引用,来了解被引用的对象是否被GC回收
 *
 *引用队列
 *  使用SoftReference，WeakReference，PhantomReference 的时候，可以关联一个ReferenceQueue。
 *  那么当垃圾回收器准备回收一个被引用包装的对象时，该引用会被加入到关联的ReferenceQueue。
 *  程序可以通过判断引用队列中是否已经加入引用,来了解被引用的对象是否被GC回收。
 *
 *
 * 场景 ：
 *  软引用和弱引用:两者都可以实现缓存功能，但软引用实现的缓存通常用在服务端，而在移动设备中的内存更为紧缺，对垃圾回收更为敏感，因此android中的缓存通常是用弱引用来实现（比如LruCache）
 *  虚引用 为了被虚引用关联的对象在被垃圾回收器回收时，能够收到一个系统通知
 *
 * 在一个Activity执行完onDestroy()之后，将它放入WeakReference中，然后将这个WeakReference类型的Activity对象与ReferenceQueue关联。
 * 这时再从ReferenceQueue中查看是否有没有该对象，如果没有，执行gc，再次查看，还是没有的话则判断发生内存泄露了。
 * 最后用HAHA这个开源库去分析dump之后的heap内存。
 *
 * eg:2021-04-28 16:54:52.604 11992-11992/com.example.space D/LeakCanary: Watching instance of com.example.space.service.ServiceActivity (com.example.space.service.ServiceActivity received Activity#onDestroy() callback) with key 47cfcd35-40b9-45c8-b648-f24d144cc927
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ====================================
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: HEAP ANALYSIS RESULT
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ====================================
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: 1 APPLICATION LEAKS
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: References underlined with "~~~" are likely causes.
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: Learn more at https://squ.re/leaks.
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: 98134 bytes retained by leaking objects
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: Signature: f3f4dc33860ab6f9f13964f6a6942ee1b545
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ┬───
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │ GC Root: Global variable in native code
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ├─ com.example.space.service.TimeService$TimeBinder instance
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Leaking: NO (TimeService↓ is not leaking)
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    this$0 instance of com.example.space.service.TimeService
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    ↓ TimeService$TimeBinder.this$0
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ├─ com.example.space.service.TimeService instance
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Leaking: NO (Service held by ActivityThread)
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    mApplication instance of android.app.Application
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    mBase instance of android.app.ContextImpl
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    ↓ TimeService.timeCallBack
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │                  ~~~~~~~~~~~~
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ├─ com.example.space.service.ServiceActivity$2$1 instance
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Leaking: UNKNOWN
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Retaining 12 B in 1 objects
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Anonymous class implementing com.example.space.service.TimeService$TimeCallBack
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    ↓ ServiceActivity$2$1.this$1
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │                          ~~~~~~
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ├─ com.example.space.service.ServiceActivity$2 instance
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Leaking: UNKNOWN
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Retaining 12 B in 1 objects
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    Anonymous class implementing android.content.ServiceConnection
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    this$0 instance of com.example.space.service.ServiceActivity with mDestroyed = true
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │    ↓ ServiceActivity$2.this$0
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: │                        ~~~~~~
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ╰→ com.example.space.service.ServiceActivity instance
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     Leaking: YES (ObjectWatcher was watching this because com.example.space.service.ServiceActivity received
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     Activity#onDestroy() callback and Activity#mDestroyed is true)
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     Retaining 98.1 kB in 1162 objects
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     key = 47cfcd35-40b9-45c8-b648-f24d144cc927
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     watchDurationMillis = 6893
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     retainedDurationMillis = 1893
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     timeService instance of com.example.space.service.TimeService
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     mApplication instance of android.app.Application
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ​     mBase instance of androidx.appcompat.view.ContextThemeWrapper
 * 2021-04-28 16:55:04.437 11992-12150/com.example.space D/LeakCanary: ====================================
 *
 * 问题 当service 结束时 handler 还是执行 导致内存泄漏
 * 解决 在service 退出时
 */
public class LeakActivity extends BaseActivity<LeakView,LeakPresenter> implements LeakView {
    private Button tvRequest;
    private TextView tvChange;

    WeakReference<String> str = new WeakReference<String>(new String("hello"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leak;
    }

    @Override
    public LeakPresenter createPresenter() {

        Log.e("Test",this.toString());
        return new LeakPresenter(this);
    }

    @Override
    public LeakView createView() {
        return this;
    }

    @Override
    public void init() {
        tvChange = (TextView) findViewById(R.id.tv_change);
        tvRequest = (Button) findViewById(R.id.tv_request);
        tvRequest.setOnClickListener(v -> {
            Log.e("Test","setOnClickListener");
            getPresenter().toRequestData(22);
        });

    }

    private void test(String s){
        Log.e("Test","TESS == "+s);
        tvChange.setText(s);
    }

    @Override
    public void onSuccess(String s) {
        Log.e("Test","onSuccess"+s+" isFinishing"+isFinishing()+" isDestroy"+isDestroyed());
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        test(s);
    }

    @Override
    public void onFailure(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    private String TAG = "Test";

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    /**
     *开启activity
     *2021-03-30 15:04:44.438 19544-19544/com.example.space E/Test: com.example.space.leak.LeakActivity@b8fe3a6
     * 2021-03-30 15:04:44.441 19544-19544/com.example.space D/Test: onCreate
     * 2021-03-30 15:04:44.446 19544-19544/com.example.space D/Test: onStart
     * 2021-03-30 15:04:44.447 19544-19544/com.example.space D/Test: onResume
     * 2021-03-30 15:04:47.916 19544-19544/com.example.space E/Test: setOnClickListener
     * 2021-03-30 15:04:47.916 19544-19544/com.example.space E/Test: toRequestData  id == 22
     *
     * 退出activity
     * 2021-03-30 15:04:50.469 19544-19544/com.example.space D/Test: onPause
     * 2021-03-30 15:04:50.790 19544-19544/com.example.space D/Test: onStop
     * 2021-03-30 15:04:50.795 19544-19544/com.example.space D/Test: onDestroy
     * 2021-03-30 15:04:57.920 19544-19651/com.example.space E/Test: 请求结束
     * 2021-03-30 15:04:57.920 19544-19651/com.example.space E/Test: mActivity != null
     * 2021-03-30 15:04:57.921 19544-19544/com.example.space E/Test: getView() != null
     * 2021-03-30 15:04:57.921 19544-19544/com.example.space E/Test: onSuccess请求网络数据成功 name == ww isFinishingtrue isDestroytrue
     * 2021-03-30 15:04:57.948 19544-19544/com.example.space E/Test: TESS == 请求网络数据成功 name == ww
     * 2021-03-30 15:04:57.949 19544-19544/com.example.space E/Test: mContext != nullcom.example.space.leak.LeakActivity@b8fe3a6
     * 开启activity
     * 2021-03-30 15:05:20.285 19544-19544/com.example.space E/Test: com.example.space.leak.LeakActivity@c8e4923
     * 2021-03-30 15:05:20.286 19544-19544/com.example.space D/Test: onCreate
     * 2021-03-30 15:05:20.290 19544-19544/com.example.space D/Test: onStart
     * 2021-03-30 15:05:20.291 19544-19544/com.example.space D/Test: onResume
     * 2021-03-30 15:05:22.607 19544-19544/com.example.space E/Test: setOnClickListener
     * 2021-03-30 15:05:22.607 19544-19544/com.example.space E/Test: toRequestData  id == 22
     * 2021-03-30 15:05:32.616 19544-19677/com.example.space E/Test: 请求结束
     * 2021-03-30 15:05:32.617 19544-19677/com.example.space E/Test: mActivity != null
     * 2021-03-30 15:05:32.617 19544-19544/com.example.space E/Test: getView() != null
     * 2021-03-30 15:05:32.617 19544-19544/com.example.space E/Test: onSuccess请求网络数据成功 name == ww isFinishingfalse isDestroyfalse
     * 2021-03-30 15:05:32.643 19544-19544/com.example.space E/Test: TESS == 请求网络数据成功 name == ww
     * 2021-03-30 15:05:32.644 19544-19544/com.example.space E/Test: mContext != nullcom.example.space.leak.LeakActivity@c8e4923
     */


}