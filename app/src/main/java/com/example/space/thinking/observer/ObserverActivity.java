package com.example.space.thinking.observer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.space.R;

public class ObserverActivity extends AppCompatActivity {
    private EditText etName;
    private TextView tvAdd;
    private EditText etNews;
    private TextView tvNotify;
    private TextView tvNews;

    private INews iNews;
    private News news;
    private String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        etName = (EditText) findViewById(R.id.et_name);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        etNews = (EditText) findViewById(R.id.et_news);
        tvNotify = (TextView) findViewById(R.id.tv_notify);
        tvNews = (TextView) findViewById(R.id.tv_news);

        news=new News();
        iNews=new INews() {
            @Override
            public void publish(String s) {
                if (!"".equals(s)){
                    if ("".equals(text)){
                        text=s;
                    }else {
                        text=text+s;
                    }

                    tvNews.setText(text);
                }
            }
        };

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(etName.getText())){
                    Person person=new Person(etName.getText().toString());
                    person.setiNews(iNews);
                    news.subscribe(person);
                    Toast.makeText(ObserverActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    etName.setText("");
                }
            }
        });

        tvNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(etNews.getText())){
                    text="";

                    news.notifyAllPeople(etNews.getText().toString());
                    Toast.makeText(ObserverActivity.this,"通知成功",Toast.LENGTH_SHORT).show();
                    etNews.setText("");
                }
            }
        });
    }
}