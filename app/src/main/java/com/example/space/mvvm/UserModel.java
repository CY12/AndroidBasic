package com.example.space.mvvm;

import com.example.space.http.Http;
import com.example.space.http.HttpUtils;
import com.example.space.recycleview.Party;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Callback;

public class UserModel {

    public void getData(String params, Callback<Party> callback){
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), params);
        // 从网络获取数据
        HttpUtils.getRequest().getParty(requestBody).enqueue(callback);
    }
}
