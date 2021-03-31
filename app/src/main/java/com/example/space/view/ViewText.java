package com.example.space.view;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewText {

    private void Test(TextView view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"ScaleX",0,1);
    }
    //Activity Window DecorView ViewRoot
//    Activity 控制器
//    Activity并不负责视图控制，它只是控制生命周期和处理事件。真正控制视图的是Window。一个Activity包含了一个Window，Window才是真正代表一个窗口。Activity就像一个控制器，统筹视图的添加与显示，以及通过其他回调方法，来与Window、以及View进行交互。
//    Window 承载器
//    Window是视图的承载器，承载视图View的显示。内部持有一个 DecorView，而这个DecorView才是 view 的根布局。
//    Window是一个抽象类，实际在Activity中持有的是其子类PhoneWindow。PhoneWindow中有个内部类DecorView，通过创建DecorView来加载Activity中设置的布局R.layout.activity_main。
//    Window 通过WindowManager将DecorView加载其中，并将DecorView交给ViewRoot，进行视图绘制以及其他交互。

/**
 * view 的绘制和 activity 的生命周期不是同步执行的 通过
 * 1.onWindowFocusChanged
 * 2.view.post(runnable)
 * 3.ViewTreeObserver
 * 4.
 */
//1.onWindowFocusChanged
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus){
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus){
//              int width = view.getMeasuredWidth();
//        }
//    }
// 2.view.post(runnable) post 将runnable 发送到消息队列的尾部，然后等待looper 调用此runnable 时，说明view 初始化成功
//    protected void onStart(){
//        super.onStart();
//        view.post(new Runnable(){
//            @Override
//            public void run(){
//                int width = view.getMeasuredWidth();
//            }
//        })
//    }

}
