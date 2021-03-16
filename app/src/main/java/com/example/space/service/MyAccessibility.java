package com.example.space.service;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class MyAccessibility extends AccessibilityService {
    private final static String TAG = "Test";

    private MyAccessibility mService;
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d("Test","service 连接");
        mService = this;
    }



    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        String className = accessibilityEvent.getClassName().toString();
        Log.d(TAG, "当前事件类型：" + eventType);
        Log.d(TAG, "当前执行类名：" + className);
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode==null)return;
        List<AccessibilityNodeInfo> list = rootNode.findAccessibilityNodeInfosByText("Accessibility");
        List<AccessibilityNodeInfo> idList = rootNode.findAccessibilityNodeInfosByViewId("com.android.space:id/Accessibility");
        List<AccessibilityNodeInfo> id2List = rootNode.findAccessibilityNodeInfosByViewId("com.android.space:id/Accessibility");
        Log.d("Test","textSize"+list.size()+" idListSize"+idList.size()+" id2ListSize"+id2List.size());
        rootNode.recycle();
        list.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
        Log.d("Test","name:"+list.get(0).getViewIdResourceName());

        // 获取事件类型，在对应的事件类型中对相应的节点进行操作
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.d(TAG, "界面改变::PackageName::" + accessibilityEvent.getPackageName() + "::ClassName::" + accessibilityEvent.getClassName());
                if (rootNode.getChildCount()!=0){
                    Log.d("Test","getChildCount"+rootNode.getChildCount());

                }
                // 获取父节点
//                rootNode.getParent();
//                // 获取子节点
//                rootNode.getChild(0);
//                // 在节点上执行一个动作，通过返回值判断是否执行成功
//                boolean bool = rootNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                bool = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
//                bool = rootNode.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
                // 通过字符串查找节点元素
//
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                Log.d(TAG, "内容改变::PackageName::" + accessibilityEvent.getPackageName() + "::ClassName::" + accessibilityEvent.getClassName());
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                Log.d(TAG, "滑动变化::PackageName::" + accessibilityEvent.getPackageName() + "::ClassName::" + accessibilityEvent.getClassName());
                break;
            default:
                break;
        }



    }
    @Override
    public void onInterrupt () {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Test","服务断开");
        mService = null;
    }

}