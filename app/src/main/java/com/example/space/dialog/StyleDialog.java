package com.example.space.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.space.R;

public class StyleDialog extends Dialog {
    public StyleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_my_style);

    }
}
