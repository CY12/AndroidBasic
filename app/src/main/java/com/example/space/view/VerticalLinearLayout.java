package com.example.space.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.flexibleview.FlexibleViewTools;

import java.util.ArrayList;
import java.util.List;

public class VerticalLinearLayout extends LinearLayout {
    private List<String> mTextList = new ArrayList<>();
    private Context mContext;
    private int textSize = 20;
    private int textColor = Color.BLACK;
    private int marginTop = 0;
    private int marginBottom = 0;
    private int marginLeft = 20;
    private int marginRight = 0;

    public VerticalLinearLayout(Context context) {
        super(context);
        mContext = context;
    }

    public VerticalLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public VerticalLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setText(String text){
        
    }

    public void setText(List<String> textList) {
        mTextList.clear();
        mTextList.addAll(textList);
        int childCount = getChildCount();
        int textCount = textList.size();
        if (childCount <= textCount) {
            for (int i = 0; i < textCount; i++) {
                if (i < childCount) {
                    TextView textView = (TextView) getChildAt(i);
                    textView.setText(textList.get(i));
                } else {
                    createTextView(textList.get(i));
                }
            }

        } else {
            for (int i = 0; i < childCount; i++) {
                if (i < textCount) {
                    TextView textView = (TextView) getChildAt(i);
                    textView.setText(textList.get(i));
                } else {
                    removeViewAt(i);
                }
            }
        }
    }

    private void createTextView(String text) {
        TextView textView = new TextView(mContext);
        textView.setEms(1);
        textView.setText(text);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        addView(textView);
        LinearLayout.LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        textView.setLayoutParams(layoutParams);
    }
}
