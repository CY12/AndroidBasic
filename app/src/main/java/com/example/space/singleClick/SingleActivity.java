package com.example.space.singleClick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.space.R;

public class SingleActivity extends AppCompatActivity {
    RadioGroup radioGroup ;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        textView = findViewById(R.id.tv_test);
        radioGroup = findViewById(R.id.rg_font);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.max:
                        textView.setTextSize(25);
                        break;
                    case R.id.mid:
                        textView.setTextSize(20);
                        break;
                    case R.id.small:
                        textView.setTextSize(15);
                        break;

                }
            }
        });

    }
}