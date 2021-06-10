package com.example.space.dispatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.space.R;
import com.example.space.view.DisView;
import com.example.space.view.DisViewGroup;

/**
 * https://www.jianshu.com/p/38015afcdb58/
 * 点击事件产生后
 *步骤1：调用dispatchTouchEvent()
 *public boolean dispatchTouchEvent(MotionEvent ev) {
 *
 *     boolean consume = false; //代表 是否会消费事件
 *
 *     // 步骤2：判断是否拦截事件
 *     if (onInterceptTouchEvent(ev)) {
 *       // a. 若拦截，则将该事件交给当前View进行处理
 *       // 即调用onTouchEvent()去处理点击事件
 *       consume = onTouchEvent (ev) ;
 *
 *     } else {
 *
 *       // b. 若不拦截，则将该事件传递到下层
 *       // 即 下层元素的dispatchTouchEvent()就会被调用，重复上述过程
 *       // 直到点击事件被最终处理为止
 *       consume = child.dispatchTouchEvent (ev) ;
 *     }
 *
 *     // 步骤3：最终返回通知 该事件是否被消费（接收 & 处理）
 *     return consume;
 *
 *
 * 分发大体流程是 从上到下 在从下到上。()
 * 首先 是调用 viewGroup的 dispatchTouchEvent;
 * dispatchTouchEvent 有 intercepted = onInterceptTouchEvent(ev); 如果拦截就消费
 *if (intercepted || mFirstTouchTarget != null) {
 *                 ev.setTargetAccessibilityFocus(false);
 *             }方法看不到具体了
 * 说是走 即调用onTouchEvent()去处理点击事件 然后返回  consume = onTouchEvent (ev) ;这是分发的方法流程
 *
 * view 分发方法
 *   public boolean dispatchTouchEvent(MotionEvent event) {
 *
 *
 *         if ( (mViewFlags & ENABLED_MASK) == ENABLED &&
 *               mOnTouchListener != null &&
 *               mOnTouchListener.onTouch(this, event)) {
 *             return true;
 *         }
 *
 *         return onTouchEvent(event);
 *   }
 *
 *   调用onTouch onTouch返回true 表示消费，
 *   不消费调用onTouchEvent 方法
 *
 *
 */
public class DispatchActivity extends AppCompatActivity {


    private DisViewGroup disViewGroup;
    private DisView disView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispacth);
        disViewGroup = (DisViewGroup) findViewById(R.id.disViewGroup);
        disView = (DisView) findViewById(R.id.disView);
        disView.setOnClickListener(v -> {

            Log.e("Test","disView onClick");
        });
    }
}