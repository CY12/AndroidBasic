package com.example.space.picture;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.space.R;

import java.io.IOException;
import java.io.InputStream;

public class AutoPictureActivity extends AppCompatActivity {
    private AutoImageView aiView;
    private ImageView ivTest;




    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_picture);
        aiView = (AutoImageView) findViewById(R.id.ai_view);
        ivTest = (ImageView) findViewById(R.id.iv_test);


        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("large.jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        inputStream = ImageUtil.getInstance().Drawable2InputStream(getDrawable(R.drawable.large));
        aiView.setImage(inputStream);
    }
}