package com.example.space.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.space.R;
import com.example.space.databinding.ActivityMvvmBinding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MvvmActivity extends AppCompatActivity {
    private ActivityMvvmBinding binding;
    private MVVMViewModel mvvmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);

        mvvmViewModel = new MVVMViewModel(getApplication(),binding);
        binding.setViewModel(mvvmViewModel);  //初始化viewModel
    }


    private void sort(){
        HashMap<String,String> hashMap = new HashMap();

        for (Map.Entry<String ,String> h: hashMap.entrySet()){
            h.getKey();
        }
        Iterator<Map.Entry<String,String>> it = hashMap.entrySet().iterator();

    }
}