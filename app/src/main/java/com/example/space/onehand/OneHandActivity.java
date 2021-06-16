package com.example.space.onehand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.example.space.R;

public class OneHandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_hand);
        findViewById(R.id.tv_start).setOnClickListener(v -> {
            showDialog();
        });
    }
    public void showDialog(){
        Dialog alert = new Dialog(getApplicationContext());
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_two,null);
        alert.setContentView(view);
        alert.show();
    }

}