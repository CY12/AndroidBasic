package com.example.space;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> name;
    public LiveData<String> getName(){
        if(name == null){
            name=new MutableLiveData<String>();
            initName();

        }
        return name;
    }

    private void initName(){
        // rebase 开发
        // rebase 开发 开发
        // rebase
        // fetch 之后提交代码
    }
}
