package com.example.space.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.space.R;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mSlideRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mSlideRv = findViewById(R.id.rv_slide);
        Class clz = mSlideRv.getClass();
        try {
            Field field = clz.getDeclaredField("DEBUG");
            field.setAccessible(true);
            field.setBoolean(clz,true);
        }catch (Exception e){
            Log.e("Test",e.toString());
        }
        SlideAdapter adapter = new SlideAdapter(this);
        mSlideRv.setAdapter(adapter);

        mSlideRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mSlideRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect,  View view,  RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
                outRect.bottom = 2;
            }
        });
        findViewById(R.id.btn_pool).setOnClickListener(v -> {
            getPoolSize();
        });

    }
    private void getPoolSize(){
        Class clz = mSlideRv.getClass();
        try {
            Field field = clz.getDeclaredField("DEBUG");
            field.setAccessible(true);
            Log.e("Test"," IS DEBUG "+field.getBoolean(clz));
        }catch (Exception e){
            Log.e("Test",e.toString());
        }

        RecyclerView.RecycledViewPool recycledViewPool = mSlideRv.getRecycledViewPool();
        Log.e("Test1","size"+recycledViewPool.getRecycledViewCount(Integer.parseInt("0")));

//        Class clz = mSlideRv.getClass();
//        Class poolClass = mSlideRv.getRecycledViewPool().getClass();;
//        Class scrapClass = null;
//        Class innerClass[] = clz.getDeclaredClasses();
//        Log.e("Test",innerClass.length+"length");
//        for (Class cls : innerClass){
//            Log.e("Test",cls.getName());
//            if (cls.getName().equals("RecycledViewPool")){
//                poolClass = cls;
//            }
//
//        }
//        if (poolClass == null) return;
//        Class iClass[] = poolClass.getDeclaredClasses();
//        for (Class cls: iClass){
//            if (cls.getName().equals("ScrapData")){
//                scrapClass = cls;
//            }
//        }
//        try {
//            Field field = scrapClass.getField("mScrapHeap");
//            field.setAccessible(true);
//            if (List.class.isAssignableFrom(field.getType())){
//                Type t = field.getGenericType();
//                if (t instanceof ParameterizedType) {
//                    ParameterizedType pt = (ParameterizedType) t;
//                    Type[] realType = pt.getActualTypeArguments();
//                    Log.e("Test","poolSize:"+realType.length);
////                    Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
////                    List<Object> curEleList = new ArrayList<>();
////                    Object actualType = actualTypeArgument.newInstance();
//                }
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
    }
}