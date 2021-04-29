package com.example.space.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.space.R;

public class DialogUtils {
    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  原view
     * @param contentView 要展示的view
     * @return window显示的左上角的xOff, yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        final int anchorWidth = anchorView.getWidth();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowDown = (anchorLoc[1] < windowHeight);
        windowPos[0] = anchorLoc[0] + anchorWidth / 2 - windowWidth / 2;
        if (isNeedShowDown) {
            windowPos[1] = anchorLoc[1] + anchorHeight;
        } else {
            windowPos[1] = anchorLoc[1] - windowHeight;
        }
        return windowPos;
    }
    public interface OnTwoBtnClickListener {
        void onClickTop();
        void onClickBottom();
    }

    /**
     * 上下两个按钮
     * @param context
     * @param top
     * @param bottom
     * @param view
     * @param listener
     */
    public static void showTwoBtnPopuWindow(Context context, String top, String bottom, View view, final OnTwoBtnClickListener listener) {
        final PopupWindow mPopupWindow = new PopupWindow(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_two, null);
        TextView tvTop = inflate.findViewById(R.id.tv_top);
        TextView tvBottom = inflate.findViewById(R.id.tv_bottom);
        tvTop.setText(top);
        tvBottom.setText(bottom);


        // 设置布局文件
        mPopupWindow.setContentView(inflate);
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置pop透明效果
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
//        mPopupWindow.setAnimationStyle(R.style.pop_add);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量
        int height = view.getHeight();
        int width = view.getWidth();
        mPopupWindow.showAsDropDown(view, width/2, -height/2);
        final Bundle bundle = new Bundle();
        tvTop.setOnClickListener(v -> {
            listener.onClickTop();
            mPopupWindow.dismiss();
        });
        tvBottom.setOnClickListener(v -> {
            listener.onClickBottom();
            mPopupWindow.dismiss();
        });

    }

}
