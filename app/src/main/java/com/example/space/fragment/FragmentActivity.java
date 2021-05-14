package com.example.space.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.space.R;
import com.example.space.base.BaseLifeActivity;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FragmentActivity extends BaseLifeActivity {
    private FrameLayout flFragment;
    private TextView tvStart;

    private TextView tvHide;
    private TextView tvShow;
    private FirstFragment firstFragment;
    private TextView tvSecond;




    @Override
    protected String getTAG() {
        return "FActivity";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        flFragment = (FrameLayout) findViewById(R.id.fl_fragment);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvHide = (TextView) findViewById(R.id.tv_hide);
        tvShow = (TextView) findViewById(R.id.tv_show);
        tvSecond = (TextView) findViewById(R.id.tv_second);


        firstFragment = new FirstFragment();
        tvStart.setOnClickListener(v -> {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_fragment,firstFragment);
            fragmentTransaction.addToBackStack("FirstFragment");
            fragmentTransaction.commit();

        });
        tvHide.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(firstFragment);
            fragmentTransaction.commit();

        });

        tvShow.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.show(firstFragment);
            fragmentTransaction.commit();

        });

        tvSecond.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_fragment,new SecondFragment());
            fragmentTransaction.addToBackStack("SecondFragment");
            fragmentTransaction.commit();
        });
    }
    public void startToFragment(int container, Fragment newFragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(container, newFragment);
        transaction.commit();
    }
//2021-03-31 14:08:30.366 29004-29004/com.example.space E/Test: FActivityonCreate
//2021-03-31 14:08:30.388 29004-29004/com.example.space E/Test: FActivityonStart
//2021-03-31 14:08:30.390 29004-29004/com.example.space E/Test: FActivityonResume
//2021-03-31 14:08:32.105 29004-29004/com.example.space E/Test: FirstFragment onAttach
//2021-03-31 14:08:32.106 29004-29004/com.example.space E/Test: FirstFragment onCreate
//2021-03-31 14:08:32.109 29004-29004/com.example.space E/Test: FirstFragment onCreateView
//2021-03-31 14:08:32.119 29004-29004/com.example.space E/Test: FirstFragment onViewCreated
//2021-03-31 14:08:32.119 29004-29004/com.example.space E/Test: FirstFragment onActivityCreated
//2021-03-31 14:08:32.119 29004-29004/com.example.space E/Test: FirstFragment onStart
//2021-03-31 14:08:32.120 29004-29004/com.example.space E/Test: FirstFragment onResume
//2021-03-31 14:08:37.094 29004-29004/com.example.space E/Test: SecondFragment onAttach
//2021-03-31 14:08:37.094 29004-29004/com.example.space E/Test: SecondFragment onCreate
//2021-03-31 14:08:37.097 29004-29004/com.example.space E/Test: FirstFragment onPause
//2021-03-31 14:08:37.097 29004-29004/com.example.space E/Test: FirstFragment onStop
//2021-03-31 14:08:37.098 29004-29004/com.example.space E/Test: FirstFragment onDestroyView
//2021-03-31 14:08:37.115 29004-29004/com.example.space E/Test: FirstFragment onDestroy
//2021-03-31 14:08:37.115 29004-29004/com.example.space E/Test: FirstFragment onDetach
//2021-03-31 14:08:37.116 29004-29004/com.example.space E/Test: SecondFragment onCreateView
//2021-03-31 14:08:37.120 29004-29004/com.example.space E/Test: SecondFragment onViewCreated
//2021-03-31 14:08:37.120 29004-29004/com.example.space E/Test: SecondFragment onActivityCreated
//2021-03-31 14:08:37.120 29004-29004/com.example.space E/Test: SecondFragment onStart
//2021-03-31 14:08:37.120 29004-29004/com.example.space E/Test: SecondFragment onResume

}