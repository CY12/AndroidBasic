package com.example.space.softinput;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flexibleview.FlexibleViewTools;
import com.example.space.R;


public class SoftInputActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//                if (name.equals("TextView") ){
//                    name = "Button";
//                }
                View view = getDelegate().createView(parent, name, context, attrs);
                return view;
            }

            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                //这个方法时 mFactory，因为 mFactory2 继承 mFactory ，所以可以不用管
                return null;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_input);
        dragView(findViewById(R.id.rv_dialog));
        LinearInterpolator linearInterpolator = new LinearInterpolator();

    }
    private int mLastY;
    private void dragView(View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = (int) event.getRawY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        int dy = y - mLastY;
                        setHeight(view,-dy);
                        break;
                }
                mLastY = y;
                return true;
            }
        });
    }
    private void setHeight(View view,int height){
        RelativeLayout.LayoutParams rlParams =(RelativeLayout.LayoutParams) view.getLayoutParams();//外层是RelativeLayout
        rlParams.height =rlParams.height + height;
        view.setLayoutParams(rlParams);
    }
}