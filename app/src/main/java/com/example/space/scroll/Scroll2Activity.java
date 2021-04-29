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

/**
 * scrollTo是绝对滚动(以view的内容的中心为原点，如果x为负值，则向右滚，y为负值向下滚)，scrollBy是相对滚动
 * 他们的相同点就是滚动的都是view中的内容，而不是view本身，view本身的getX，getY方法得到的值是不会变的
 *
 */
public class Scroll2Activity extends AppCompatActivity {
    private float mx,my;
    ValueAnimator valueAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll2);

        final ImageView switcherView = (ImageView) this.findViewById(R.id.img);
        valueAnimator = ValueAnimator.ofInt(0,1000);

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

    public void onDestroy(){
        super.onDestroy();
        valueAnimator.end();
    }
}