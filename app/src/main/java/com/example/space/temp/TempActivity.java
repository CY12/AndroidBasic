package com.example.space.temp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.example.space.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TempActivity extends AppCompatActivity {
//    String[][][] options1Items = new String[][][]{{{"安徽", "河北", "北京"},{"北京", "河北", "北京"},{"河北", "河北", "北京"}}};

//    List<String> options1Items = new ArrayList<String>(){};
//    List<String> options1Items = new ArrayList<String>();
//    List<String> options2Items = new ArrayList<String>();
//    List<String>options3Items = new ArrayList<String>();
    //List<List<List<T>>>
    List<String> options1Items = new ArrayList<String>(Arrays.asList("apple1", "banana2", "orange3"));
    List<String> options11Items = new ArrayList<String>(Arrays.asList("apple11", "banana11", "orange11"));
    List<String> options111Items = new ArrayList<String>(Arrays.asList("apple111", "banana111", "orange111"));
//    List<String> options2Items = new ArrayList<String>(Arrays.asList("apple1", "banana1", "orange1"));
    //List<String> options3Items = new ArrayList<String>(Arrays.asList("apple2", "banana2", "orange2"));
    List<List<List<String>>> options3Items = new ArrayList<List<List<String>>>();
    List<List<String>> options2Items = new ArrayList<List<String>>();

    private PhotoView ivTemp;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.e("Test",Thread.currentThread().getName());
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("Test","2s后");

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        ivTemp =  findViewById(R.id.iv_temp);
        //http://121.196.167.157:9090/image/1611718168025.jpg
//        ivTemp.setImageResource(R.mipmap.big);
//        Glide.with(this).load(R.mipmap.big).into(ivTemp);
//        Glide.with(this).load("http://121.196.167.157:9090/image/1611718168025.jpg").into(ivTemp);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.contentproviderdemo","com.example.contentproviderdemo.MainActivity2");
                intent.putExtra("app","space");
                startActivityForResult(intent,200);
//                handler.post(null);
//                Log.e("Test","post之后");
            }
        });
        options2Items.add(options1Items);
        options2Items.add(options11Items);
        options2Items.add(options111Items);
        
        options3Items.add(options2Items);
        options3Items.add(options2Items);
        options3Items.add(options2Items);


        OptionsPickerView pvOptions = new OptionsPickerBuilder(TempActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 , View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(option2)
//                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                tvOptions.setText(tx);

                Log.d("Test","position1"+options1+"position2 "+option2+"position3 "+options3);
            }
        }).build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Test","request"+requestCode+"result"+resultCode);
        if (requestCode == 200 && resultCode == 200){
            Log.e("Test","成功返回");
            if (data != null){
                Log.e("Test","获取到信息"+data.getStringExtra("data"));
            }
        }
    }
}