package com.example.space.leak;

import com.example.space.base.BaseView;

public interface LeakView extends BaseView {
    void onSuccess(String s);
    void onFailure(String s);
}
