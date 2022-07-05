package com.example.space.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.example.space.utils.ViewBindingUtil;

import org.jetbrains.annotations.Nullable;

public abstract class BaseViewBindingActivity<VB extends ViewBinding> extends AppCompatActivity {

    private VB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewBindingUtil.inflateWithGeneric(this, getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public VB getBinding() {
        return binding;
    }
}

