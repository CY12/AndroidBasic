package com.example.space.softinput;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.space.R;

/**
 * https://www.jianshu.com/p/cd9e197a5c04
 */
public class InputEmojiActivity extends AppCompatActivity {
    private ImageView ivAdd;
    private EditText etInput;
    private TextView tvShow;
    private TextView tvConfirm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_emoji);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        etInput = (EditText) findViewById(R.id.et_input);
        tvShow = (TextView) findViewById(R.id.tv_show);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        SmileUtils.addPattern("/头像", R.mipmap.ic_tou);
        ivAdd.setOnClickListener(v -> {
            SmileUtils.insertIcon(etInput,1000,100,"/头像");
            Log.e("Test","input:"+etInput.getText().toString());
        });
        tvConfirm.setOnClickListener(v -> {
            String t = etInput.getText().toString();
            SpannableString s = new SpannableString(t);
            SmileUtils.addSmiles(this,s);
            tvShow.setText(s);
            etInput.setText("");
        });
    }

    public static void insertIcon(EditText editText, int maxLength, int size, String name) {

        String curString = editText.toString();
        if ((curString.length() + name.length()) > maxLength) {
            return;
        }



        Drawable drawable = editText.getResources().getDrawable(R.mipmap.ic_tou);
        if (drawable == null)
            return;

        drawable.setBounds(0, 0, size, size);//这里设置图片的大小
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(name);
        spannableString.setSpan(imageSpan, 0, spannableString.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);


        int index = Math.max(editText.getSelectionStart(), 0);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(editText.getText());
        spannableStringBuilder.insert(index, spannableString);

        editText.setText(spannableStringBuilder);
        editText.setSelection(index + spannableString.length());
    }
}