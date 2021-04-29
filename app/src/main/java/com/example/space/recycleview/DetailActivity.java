package com.example.space.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.space.R;
import com.example.space.http.HttpUtils;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private TextView total;
    private TextView tvOfficeLevelWage;
    private View line;
    private TextView tvRankSalary;
    private TextView tvOther;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };
    Detail detail = new Detail(){
        @Override
        public String toString() {
            return super.toString();
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        total = (TextView) findViewById(R.id.total);
        tvOfficeLevelWage = (TextView) findViewById(R.id.tv_officeLevelWage);
        line = (View) findViewById(R.id.line);
        tvRankSalary = (TextView) findViewById(R.id.tv_rankSalary);
        tvOther = (TextView) findViewById(R.id.tv_other);
        if (getIntent().getExtras() == null) return;
        int total1 = getIntent().getExtras().getInt("total");
        total.setText(total1+"元");
        String date = getIntent().getExtras().getString("date");
        getDetail(date);



    }

    private void getDetail(String date){
        DetailParams detailParams = new DetailParams();
        detailParams.setDate(date);
        detailParams.setLoginName("210283199703127211");
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        String json = gb.create().toJson(detailParams);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        HttpUtils.getRequest().getDetail(requestBody).enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                if (response != null && response.code() == 200 && response != null){
                    tvOfficeLevelWage.setText(response.body().getData().getOfficeLevelWage()+"元");
                    tvRankSalary.setText(response.body().getData().getRankSalary()+"元");
                    tvOther.setText(response.body().getData().getOther()+"元");
                }
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {

            }
        });
    }
}