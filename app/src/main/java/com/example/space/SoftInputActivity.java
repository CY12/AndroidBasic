package com.example.space;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Surface;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flexibleview.FlexibleViewTools;


public class SoftInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_input);


    }
    public <T> String getThing(T t){
        return t.toString();
    }

    public <F> F getF(F f){
        return f;
    }
}