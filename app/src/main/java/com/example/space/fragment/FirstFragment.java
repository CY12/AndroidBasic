package com.example.space.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.space.R;
import com.example.space.base.BaseFragment;

public class FirstFragment extends BaseFragment {

    private TextView tvText;
    private TextView tvButton;





    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView(View view) {
        tvText = (TextView) view.findViewById(R.id.tv_text);
        tvButton = (TextView) view.findViewById(R.id.tv_button);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected String getTAG() {
        return "FirstFragment";
    }
}
