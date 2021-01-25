package com.example.space;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class DialogTip extends Dialog {


    public DialogTip(@NonNull Context context) {
        super(context);
    }

    public DialogTip(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tip);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        getWindow().setWindowAnimations(R.style.main_menu_animStyle);
        //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //一定要在setContentView之后调用，否则无效

    }
}
