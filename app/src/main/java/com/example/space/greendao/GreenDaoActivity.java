package com.example.space.greendao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.space.R;
import com.example.space.bean.Student;
import com.example.space.dao.DbController;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoActivity extends AppCompatActivity {

    private DbController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        initData();
    }
    private void initData(){
        Student wm=new Student(null,"2003","王明","男","三");
        Student xh=new Student(null,"2004","小红","女","三");
        Student xm=new Student(null,"2005","小明","男","三");
        Student xm1=new Student(null,"2005","王明","男","三");

        dbController=DbController.getInstance(this);
        dbController.insertOrReplace(wm);
        dbController.insertOrReplace(xh);
        dbController.insertOrReplace(xm);
        List<Student> list=new ArrayList<>();
        list=dbController.searchAll();

        dbController.insertOrReplace(xm1);


    }
}