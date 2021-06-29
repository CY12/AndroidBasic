package com.example.space.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        view.findViewById(R.id.tv_findFragment).setOnClickListener(v -> {

            FragmentManager manager = getActivity().getSupportFragmentManager();
            Fragment fragment = manager.findFragmentByTag("ThirdFragment");

            if (fragment != null){
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(fragment);
                transaction.commit();
                Log.e("Test","remove Conversation ");
            }else {
                Log.e("Test","fragment == null ");
            }

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
