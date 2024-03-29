package com.example.space.softinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.space.R;
import com.example.space.base.BaseLifeActivity;

public class SoftInputActivity extends BaseLifeActivity {
    private EditText etSend;


    @Override
    protected String getTAG() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_input2);
        etSend = (EditText) findViewById(R.id.et_send);

        findViewById(R.id.tv_confirm).setOnClickListener(v -> {
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)etSend.getLayoutParams();
//            Log.e("Test","layoutParams.bottomMargin"+layoutParams.bottomMargin);

            startActivity(SoftInputActivity3.class);
        });
        findViewById(R.id.tv_emoji).setOnClickListener(v -> {
            startActivity(InputEmojiActivity.class);
        });


        findViewById(R.id.tv_click).setOnClickListener(v -> {
        });
    }
}