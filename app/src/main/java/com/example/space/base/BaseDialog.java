package com.example.space.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.space.R;

public class BaseDialog extends Dialog {
    private String text;
    private TextView textView;
    private TextView cancel;

    public BaseDialog(@NonNull Context context, String text) {
        super(context);
        this.text = text;
    }


    @Override
    protected void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.dialog_base);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(true);
        textView = findViewById(R.id.tv_dialog);
        cancel = findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        if (text != null) {
            textView.setText(text);
        }
    }
}
