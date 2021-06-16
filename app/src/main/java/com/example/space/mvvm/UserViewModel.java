package com.example.space.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.space.recycleview.Party;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    private UserModel userModel = new UserModel();

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        String param = "";
        userModel.getData(param, new Callback<Party>() {
            @Override
            public void onResponse(Call<Party> call, Response<Party> response) {
                // users赋值
//                users.setValue();
            }

            @Override
            public void onFailure(Call<Party> call, Throwable t) {

            }
        });
        // Do an asynchronous operation to fetch users.
    }
}
