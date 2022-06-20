package com.example.space.textviw

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import com.example.space.R

open class KKTextView : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        initAttrs(context, attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        initAttrs(context, attrs, defStyleAttr)
    }

    private val gradientDrawable by lazy {
        GradientDrawable()
    }


    private var orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT
    init {
        includeFontPadding = false
    }

    private fun initAttrs(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        val typeArray =
            context?.obtainStyledAttributes(attrs, R.styleable.KKTextView, defStyleAttr, 0)
        typeArray?.apply {
            val corners = typeArray.getDimensionPixelSize(R.styleable.KKTextView_corners, 0)
            val solidColor = typeArray.getColorStateList(R.styleable.KKTextView_solidColor)
            val topLeftRadius = typeArray.getDimensionPixelSize(R.styleable.KKTextView_topLeftRadius, 0)
            val topRightRadius = typeArray.getDimensionPixelSize(R.styleable.KKTextView_topRightRadius, 0)
            val bottomLeftRadius = typeArray.getDimensionPixelSize(R.styleable.KKTextView_bottomLeftRadius, 0)
            val bottomRightRadius = typeArray.getDimensionPixelSize(R.styleable.KKTextView_bottomRightRadius, 0)
            val strokeWidth = typeArray.getDimensionPixelSize(R.styleable.KKTextView_strokeWidth, 0)
            val strokeColor = typeArray.getColorStateList(R.styleable.KKTextView_strokeColor)
            val startColor = typeArray.getColor(R.styleable.KKTextView_startColor,0)
            val endColor = typeArray.getColor(R.styleable.KKTextView_endColor,0)
            val colorOrientation = typeArray.getInt(R.styleable.KKTextView_gradientOrientation,0)
            val shape = Shape.Builder().setCorners(corners)
                .setSolidColor(solidColor)
                .setStrokeWidth(strokeWidth)
                .setStrokeColor(strokeColor)
                .setTopLeftRadius(topLeftRadius)
                .setTopRightRadius(topRightRadius)
                .setBottomLeftRadius(bottomLeftRadius)
                .setBottomRightRadius(bottomRightRadius)
                .setStartColor(startColor)
                .setEndColor(endColor)
                .setColorOrientation(colorOrientation)
                .build()
            setShape(shape)

        }
        typeArray?.recycle()
    }

    override fun setIncludeFontPadding(includepad: Boolean) {
        super.setIncludeFontPadding(false)
    }

    /**
     * 一次性设置完整的属性
     */
    fun setShape(shape: Shape) {
        if (shape.isEmpty()) return
        if (shape.corners != 0){
            gradientDrawable.cornerRadius = shape.corners.toFloat()
        }
        if (shape.strokeWidth != 0){
            gradientDrawable.setStroke(shape.strokeWidth,shape.strokeColor)
        }
        if (shape.solidColor != null){
            gradientDrawable.color = shape.solidColor
        }
        if (!shape.isCornerRadiusEmpty()){
            gradientDrawable.cornerRadii = floatArrayOf(
                shape.topLeftRadius.toFloat(),
                shape.topLeftRadius.toFloat(),
                shape.topRightRadius.toFloat(),
                shape.topRightRadius.toFloat(),
                shape.bottomRightRadius.toFloat(),
                shape.bottomRightRadius.toFloat(),
                shape.bottomLeftRadius.toFloat(),
                shape.bottomLeftRadius.toFloat()
            )
        }
        if (shape.startColor != 0 && shape.endColor != 0){
            val colorList = intArrayOf(shape.startColor,shape.endColor)
            gradientDrawable.colors = colorList
        }
        gradientDrawable.orientation = getOrientation(shape.orientation)
        background = gradientDrawable
    }

    fun setShape(corners: Int, color: Int) {
        setShape(Shape(corners, ColorStateList.valueOf(color)))
    }


    /**
     * 可组合的属性
     */
    fun setCorners(radius: Float): KKTextView {
        gradientDrawable.cornerRadius = radius
        return this
    }

    fun setCorners(
        topLeftRadius: Float,
        topRightRadius: Float,
        bottomRightRadius: Float,
        bottomLeftRadius: Float
    ): KKTextView {
        //1、2两个参数表示左上角，3、4表示右上角，5、6表示右下角，7、8表示左下角
        gradientDrawable.cornerRadii = floatArrayOf(
            topLeftRadius,
            topLeftRadius,
            topRightRadius,
            topRightRadius,
            bottomRightRadius,
            bottomRightRadius,
            bottomLeftRadius,
            bottomLeftRadius
        )
        background = gradientDrawable
        return this
    }

    fun setSolidColor(color: Int): KKTextView {
        gradientDrawable.setColor(color)
        return this
    }


    fun setStroke(width: Int, color: Int): KKTextView {
        gradientDrawable.setStroke(width, color)
        background = gradientDrawable
        return this
    }

    fun setColorList(colorList: IntArray): KKTextView{
        gradientDrawable.orientation = orientation
        gradientDrawable.colors = colorList
        background = gradientDrawable
        return this
    }

    fun setOrientation(orientation: GradientDrawable.Orientation): KKTextView{
        this.orientation = orientation
        gradientDrawable.orientation = orientation
        return this
    }

    private fun getOrientation(orientation: Int): GradientDrawable.Orientation{
        when(orientation){
            0 -> return GradientDrawable.Orientation.LEFT_RIGHT
            1 -> return GradientDrawable.Orientation.RIGHT_LEFT
            2 -> return GradientDrawable.Orientation.BOTTOM_TOP
            3 -> return GradientDrawable.Orientation.TOP_BOTTOM
            4 -> return GradientDrawable.Orientation.BR_TL
            5 -> return GradientDrawable.Orientation.BL_TR
            6 -> return GradientDrawable.Orientation.TL_BR
            7 -> return GradientDrawable.Orientation.TR_BL
        }
        return GradientDrawable.Orientation.LEFT_RIGHT
    }

}