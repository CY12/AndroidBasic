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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.example.space.MainActivity;
import com.example.space.R;
import com.example.space.base.BaseLifeActivity;
import com.example.space.bean.Child;
import com.example.space.bean.Parent;
import com.example.space.bean.Student;
import com.example.space.recycleview.Salary;
import com.example.space.utils.JsonUtils;
import com.example.space.view.VerticalLinearLayout;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Route(path = "/temp/temp2")
public class TempActivity extends BaseLifeActivity {
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

    private VerticalLinearLayout llVertical;


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
    protected String getTAG() {
        return "TempActivity";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        ivTemp =  findViewById(R.id.iv_temp);
        llVertical = (VerticalLinearLayout) findViewById(R.id.ll_vertical);
        try {
            JSONObject jsonObject = new JSONObject("");
            JSONArray jsonArray = new JSONArray("");
            Gson gson = new Gson();
            String s = "sda";
            Salary salary = gson.fromJson(s,Salary.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Glide.with(this).load("http://121.196.167.157:9090/image/1611718168025.jpg").into(ivTemp);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testJson();
//                Intent intent = new Intent(TempActivity.this, MainActivity.class);
//                startActivity(intent);
//                Intent intent = new Intent();
//                intent.setClassName("com.example.contentproviderdemo","com.example.contentproviderdemo.MainActivity2");
//                intent.putExtra("app","space");
//                startActivityForResult(intent,200);
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
        List<String> list = new ArrayList<>();
        list.add("山有木兮木有枝");
        list.add("心悦君兮君不知");
        llVertical.setText(list);

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

    private void paramsTest(int...params){
        if (params.length == 0)return;
        for (int i=0;i<params.length;i++){
            Log.e("Test",params[i]+"");

        }
    }

    private void testJson(){
        Gson gson = new Gson();
        JsonStudent jsonStudent = new JsonStudent();
        jsonStudent.setName("王琳");
        jsonStudent.setLocation(" Sao Francisco");
        jsonStudent.setMan(false);
        jsonStudent.setAge(19f);
        List list = new ArrayList();
        list.add("sss");
        list.add("hhh");

        jsonStudent.setList(list);
        Log.e("Test","JsonStudent:"+jsonStudent.toString());
        String json = gson.toJson(jsonStudent);
        Log.e("Test","Json"+json);
        Log.e("Test","FastJson"+ JSON.toJSON(jsonStudent));
       JsonStudent jsonStudent1 =  JsonUtils.parseToJson(JsonStudent.class,json);
        Log.e("Test","JsonUtils 后 JsonStudent:"+jsonStudent1.toString());

    }

}