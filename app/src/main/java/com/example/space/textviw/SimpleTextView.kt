package com.example.space.textviw

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

open class SimpleTextView : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        includeFontPadding = false
    }

    override fun setIncludeFontPadding(includepad: Boolean) {
        super.setIncludeFontPadding(false)
    }
}
