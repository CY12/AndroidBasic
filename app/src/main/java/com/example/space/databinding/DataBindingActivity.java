package com.example.space.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.os.Bundle;
import android.util.Log;


import com.example.space.BR;
import com.example.space.R;

public class DataBindingActivity extends AppCompatActivity {
    private final static String t = "test";
    private Student student;
    private String str = " notifyPropertyChanged:刷新单个，需要@Bindable 生成BR.;\nnotifyChange()：直接更新所有";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBinding1Binding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding1);
        student = new Student("Tom", "Smd", "boy");
        dataBinding.setStu(student);
        dataBinding.setHelper(new StudentHelper());
        //dataBinding.tvAddr.setText("Paris");

        student.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.name) {
                    Log.d(t, "BR.name: " + BR.name + " sender:" + sender.toString());

                } else if (propertyId == BR._all) {
                    Log.d(t, "BR._all" + BR._all);
                }
            }
        });
    }

    public class StudentHelper {
        public void changeName() {
            student.setName("Bob");
            student.setSex("sss");
        }

        public void changeAll() {
            student.resetAll(new Student("Alice", "San Francisco", "girl"));
        }

        public void showToast() {
            //showDialog(str);
        }
    }


}