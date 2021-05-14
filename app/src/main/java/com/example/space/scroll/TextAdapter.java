package com.example.space.scroll;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.space.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TextAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    public TextAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_text,integer+"");
    }
}
