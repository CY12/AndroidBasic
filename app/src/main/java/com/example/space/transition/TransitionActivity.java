package com.example.space.transition;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Pair;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.space.R;

public class TransitionActivity extends AppCompatActivity {
    private TextView tvFade;
    private TextView tvBottom;
    private TextView tvShare;
    private ImageView ivHead;
    private TextView tvName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        tvFade = (TextView) findViewById(R.id.tv_fade);
        tvBottom = (TextView) findViewById(R.id.tv_bottom);
        tvShare = (TextView) findViewById(R.id.tv_share);
        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        getWindow().setExitTransition(new Slide(Gravity.LEFT));
        findViewById(R.id.tv_simple).setOnClickListener(v -> {
            startActivity(new Intent(this,AimActivity.class));
        });
        tvFade.setOnClickListener(v -> {
//            Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.sidle);
//            //退出时使用
//            getWindow().setExitTransition(explode);
//            //第一次进入时使用
//            getWindow().setEnterTransition(explode);
//            //再次进入时使用
//            getWindow().setReenterTransition(explode);

            getWindow().setEnterTransition(new Fade().setDuration(2000));
            getWindow().setExitTransition(new Fade().setDuration(2000));
            startActivity();
        });

        tvBottom.setOnClickListener(v -> {
            startActivity();
            overridePendingTransition(R.anim.activity_in,R.anim.activity_out);// 不起作用
        });

        tvShare.setOnClickListener(v -> {
            startActivity(new Intent(this, AimActivity.class),
                    ActivityOptions.makeSceneTransitionAnimation(this,
                            Pair.create(ivHead,"iv_Head"),
                            Pair.create(tvName,"tv_name"))
                            .toBundle());

//            ActivityOptions.makeSceneTransitionAnimation
//                    (this, ivHead, "ivHead")
        });
    }
    private void startActivity(){
        startActivity(new Intent(this,AimActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}