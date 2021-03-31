package com.example.space.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.example.space.R;
import com.example.space.base.BaseFragment;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class ThirdFragment extends BaseFragment {

    private TextView tvText;
    private TextView tvButton;
    private TextView tvStart;




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_thrid;
    }

    @Override
    protected void initView(View view) {
        tvText = (TextView) view.findViewById(R.id.tv_text);
        tvButton = (TextView) view.findViewById(R.id.tv_button);
        tvStart = (TextView) view.findViewById(R.id.tv_start);

        tvButton.setOnClickListener(v -> {
            getFragmentManager().popBackStack("ThirdFragment",POP_BACK_STACK_INCLUSIVE);

        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected String getTAG() {
        return "ThirdFragment";
    }
}
