package com.example.space.thinking.builder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.space.R;

public class BuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);
        Home home=new Home.Builder("asll")
                .setGarden("hy")
                .build();
    }
}