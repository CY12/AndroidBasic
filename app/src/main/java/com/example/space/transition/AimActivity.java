package com.example.space.transition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.space.R;

public class AimActivity extends AppCompatActivity {
    private ImageView ivHead;
    private TextView tvName;
    private TextView tvBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aim);
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.sidle);
        getWindow().setEnterTransition(slide);
        getWindow().setExitTransition(new Slide(Gravity.TOP).setDuration(400));
//        getWindow().setEnterTransition(new Fade().setDuration(2000));
        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvBack = (TextView) findViewById(R.id.tv_back);

        tvBack.setOnClickListener(v -> {
            Log.e("Test","tvBack");
            finish();

        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }
}