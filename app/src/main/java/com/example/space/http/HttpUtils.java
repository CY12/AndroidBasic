package com.example.space.http;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static volatile Http http;

    public static Http getRequest() {
        if (http == null) {
            synchronized (HttpUtils.class){
                if (http == null){
                    http = getHttp();
                }
            }
        }
        return http;
    }
    private static Http getHttp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("http",message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://20.1.120.222:9009/wage/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//121.196.167.157
        return retrofit.create(Http.class);
    }
}
