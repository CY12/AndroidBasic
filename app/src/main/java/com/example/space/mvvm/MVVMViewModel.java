package com.example.space.mvvm;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.space.BR;
import com.example.space.databinding.ActivityMvvmBinding;

public class MVVMViewModel  extends BaseObservable {
    private ActivityMvvmBinding binding;
    private MVVMModel mvvmModel;
    private String Input;
    private String result;

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
//        notifyPropertyChanged(com.example.mvvmdemo2.BR.result);
    }
    //    一般需要传入Application对象，方便在ViewModel中使用application
    //    比如sharedpreferences需要使用
    public MVVMViewModel(Application application, ActivityMvvmBinding binding) {
        this.binding=binding;
        mvvmModel = new MVVMModel();

    }

    public void getData(View view){

        Input = binding.etAccount.getText().toString();
        Log.d("Test","getData"+"  Input:"+Input );
        mvvmModel.getAccountData(Input, new MCallback() {
            @Override
            public void onSuccess(Account account) {
                String info = account.getName() + "|" + account.getLevel();
                Log.d("Test","onSuccess"+ info);
                setResult(info);
            }

            @Override
            public void onFailed() {
                Log.d("Test","onFailed"+ "消息获取失败");
                setResult("消息获取失败");
            }
        });
    }

}
