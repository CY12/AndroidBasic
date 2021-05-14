package com.example.space.thread.leak;

import com.example.space.base.BaseView;

public interface LeakView extends BaseView {
    void onSuccess(String s);
    void onFailure(String s);
}
