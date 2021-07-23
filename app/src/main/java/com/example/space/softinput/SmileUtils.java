package com.example.space.softinput;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.EditText;

import com.example.space.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmileUtils {
//    static {
//        addPattern("/头像", R.mipmap.ic_tou);
//    }

    private static final Map<Pattern, Integer> emoticons = new HashMap<Pattern, Integer>();

    /**
     * * 文本对应的资源
     * * @param string 需要转化文本
     * * @return
     */
    public static int getRedId(String string) {
        for (Map.Entry<Pattern, Integer> entry : emoticons.entrySet()) {
            Matcher matcher = entry.getKey().matcher(string);
            while (matcher.find()) {
                return entry.getValue();
            }
        }
        return -1;
    }

    public static void addPattern(String emojiText,int icon){
        emoticons.put(Pattern.compile(Pattern.quote(emojiText)), icon);
    }

    /**
     * 文本转化表情处理
     *
     * @param editText  要显示的EditText
     * @param maxLength 最长高度
     * @param size      显示大小
     * @param name      需要转化的文本
     */
    public static void insertIcon(EditText editText, int maxLength, int size, String name) {

        String curString = editText.toString();
        if ((curString.length() + name.length()) > maxLength) {
            return;
        }

        int resId = SmileUtils.getRedId(name);

        Drawable drawable = editText.getResources().getDrawable(resId);
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

    /**
     * replace existing spannable with smiles
     *
     * @param context   上下文
     * @param spannable 显示的span
     * @return 是否添加
     */
    public static boolean addSmiles(Context context, Spannable spannable) {
        boolean hasChanges = false;
        for (Map.Entry<Pattern, Integer> entry : emoticons.entrySet()) {
            Matcher matcher = entry.getKey().matcher(spannable);
            while (matcher.find()) {
                boolean set = true;
                for (ImageSpan span : spannable.getSpans(matcher.start(),
                        matcher.end(), ImageSpan.class))
                    if (spannable.getSpanStart(span) >= matcher.start()
                            && spannable.getSpanEnd(span) <= matcher.end())
                        spannable.removeSpan(span);
                    else {
                        set = false;
                        break;
                    }
                if (set) {
                    hasChanges = true;
                    spannable.setSpan(new ImageSpan(context, entry.getValue()),
                            matcher.start(), matcher.end(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return hasChanges;
    }


}
