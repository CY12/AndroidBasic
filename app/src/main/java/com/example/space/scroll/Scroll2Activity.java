package com.example.space.scroll;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.space.R;

public class Scroll2Activity extends AppCompatActivity {
    private float mx,my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll2);

        final ImageView switcherView = (ImageView) this.findViewById(R.id.img);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,1000);

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(100000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                switcherView.scrollBy(1,0);



            }
        });



        valueAnimator.start();


//        switcherView.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View arg0, MotionEvent event) {
//
//                float curX, curY;
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
//                        mx = event.getX();
//                        my = event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        curX = event.getX();
//                        curY = event.getY();
//                        switcherView.scrollBy((int) (mx - curX), (int) (my - curY));
//                        mx = curX;
//                        my = curY;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        curX = event.getX();
//                        curY = event.getY();
//                        switcherView.scrollBy((int) (mx - curX), (int) (my - curY));
//                        break;
//                }
//
//                return true;
//            }
//        });

    }
}