package com.example.space.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.space.R;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        /**
         * 对user表进行操作
         */

        // 设置URI
        Uri uri_user = Uri.parse("content://cn.scu.myprovider/user");

        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("_id", 3);
        values.put("name", "Iverson");


        // 获取ContentResolver
        ContentResolver resolver =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver.insert(uri_user,values);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri_user, new String[]{"_id","name"}, null, null, null);
        while (cursor.moveToNext()){
            Log.e("Test","contentProvider "+ ("query book:" + cursor.getInt(0) +" "+ cursor.getString(1)));

            // 将表中数据全部输出
        }
        cursor.close();
        // 关闭游标

        /**
         * 对job表进行操作
         */
        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        Uri uri_job = Uri.parse("content://cn.scu.myprovider/job");

        // 插入表中数据
        ContentValues values2 = new ContentValues();
        values2.put("_id", 3);
        values2.put("job", "NBA Player");

        // 获取ContentResolver
        ContentResolver resolver2 =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver2.insert(uri_job,values2);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id","job"}, null, null, null);
        while (cursor2.moveToNext()){
            Log.e("Test","contentProvider "+"query job:" + cursor2.getInt(0) +" "+ cursor2.getString(1));

            // 将表中数据全部输出
        }
        cursor2.close();
        // 关闭游标
//
//        /**
//         * 对user表进行操作
//         */
//
//        // 设置URI
//        Uri uri_user = Uri.parse("content://cn.scu.myprovider/user");
//
//        // 插入表中数据
//        ContentValues values = new ContentValues();
//        values.put("_id", 4);
//        values.put("name", "Jordan");
//
//
//        // 获取ContentResolver
//        ContentResolver resolver =  getContentResolver();
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
//        resolver.insert(uri_user,values);
//
//        // 通过ContentResolver 向ContentProvider中查询数据
//        Cursor cursor = resolver.query(uri_user, new String[]{"_id","name"}, null, null, null);
//        while (cursor.moveToNext()){
//            Log.e("Test"," c2 query book:" + cursor.getInt(0) +" "+ cursor.getString(1));
//            // 将表中数据全部输出
//        }
//        cursor.close();
//        // 关闭游标
//
//        /**
//         * 对job表进行操作
//         */
//        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
//        Uri uri_job = Uri.parse("content://cn.scu.myprovider/job");
//
//        // 插入表中数据
//        ContentValues values2 = new ContentValues();
//        values2.put("_id", 4);
//        values2.put("job", "NBA Player");
//
//        // 获取ContentResolver
//        ContentResolver resolver2 =  getContentResolver();
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
//        resolver2.insert(uri_job,values2);
//
//        // 通过ContentResolver 向ContentProvider中查询数据
//        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id","job"}, null, null, null);
//        while (cursor2.moveToNext()){
//            Log.e("Test","  c2 "+"query job:" + cursor2.getInt(0) + " " + cursor2.getString(1));
//            // 将表中数据全部输出
//        }
//        cursor2.close();
//        // 关闭游标
       }
}