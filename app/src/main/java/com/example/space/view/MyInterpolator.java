package com.example.space.view;

import android.view.animation.Interpolator;

public class MyInterpolator implements Interpolator {
    /**
     * input 参数是一个 float 类型，它取值范围是 0 到 1，表示当前动画的进度，取 0 时表示动画刚开始，
     * 取 1 时表示动画结束，取 0.5 时表示动画中间的位置，其它类推
     *
     * 对于 input 参数，它表示的是当前动画的进度，匀速增加的
     * input 参数相当于时间的概念
     * @param input
     * @return
     */
    @Override
    public float getInterpolation(float input) {
        float x=2.0f*input-1.0f;
        return 0.5f*(x*x*x + 1.0f);
    }
}
