package com.example.space.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.space.R;
import com.example.space.base.BaseFragment;

import java.util.List;

public class SecondFragment extends BaseFragment {

    private TextView tvText;
    private TextView tvButton;
    private TextView tvStart;







    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initView(View view) {
        tvText = (TextView) view.findViewById(R.id.tv_text);
        tvButton = (TextView) view.findViewById(R.id.tv_button);
        tvStart = (TextView) view.findViewById(R.id.tv_start);

        tvStart.setOnClickListener(v -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_fragment,new ThirdFragment(),"ThirdFragment");
            transaction.addToBackStack("ThirdFragment");
            transaction.commit();
        });

        tvButton.setOnClickListener(v -> {
            List<Fragment> list = getFragmentManager().getFragments();
            Log.e("Test","fragment size "+list.size());
            getFragmentManager().popBackStack();
//           getFragmentManager().beginTransaction().remove(this).commit();
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected String getTAG() {
        return "SecondFragment";
    }


}
