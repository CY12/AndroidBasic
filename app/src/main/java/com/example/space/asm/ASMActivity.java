package com.example.space.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.space.base.BaseViewBindingActivity;
import com.example.space.databinding.ActivityAsmactivityBinding;

public class ASMActivity extends BaseViewBindingActivity<ActivityAsmactivityBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectUser injectUser = new InjectUser();
        injectUser.setAge(22);
        injectUser.setName("Name ");
        binding.tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                injectUser.fly();
                injectUser.run();
            }
        });


    }
}