package com.example.space.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.example.space.R;

public class AnimatorActivity extends AppCompatActivity {

    GradientView gradientView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        gradientView = findViewById(R.id.gradientView);
        ObjectAnimator animator = ObjectAnimator.ofObject(gradientView,"color",new ColorEvaluator(), "#0000FF", "#FF0000");
        animator.setDuration(8000);
        animator.start();
    }

}


