package com.example.space.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.space.R;

public abstract class BaseFragment extends Fragment {
    static String TAG = "Test";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, getTAG()+" onCreateView");
        return inflater.inflate(getLayoutId(), container, false);
    }


    protected abstract int getLayoutId();
    protected abstract void initView(View view);
    protected abstract void initData();
    protected abstract String getTAG();
    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, getTAG()+" onStart");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, getTAG()+" onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, getTAG()+" onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, getTAG()+" onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, getTAG()+" onStop");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, getTAG()+" onViewCreated");
        initView(view);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, getTAG()+" onDestroyView");
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Log.e(TAG, getTAG()+" onCreate");
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Log.e(TAG, getTAG()+" onAttach");
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        Log.e(TAG, getTAG()+" onActivityCreated");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, getTAG()+" onDetach");
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        Log.e(TAG, getTAG()+" onSaveInstanceState");
    }
}
