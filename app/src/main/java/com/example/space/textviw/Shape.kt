package com.example.space.textviw

import android.content.res.ColorStateList

class Shape {
    var corners: Int = 0
    var solidColor: ColorStateList? = null
    var topLeftRadius: Int = 0
    var topRightRadius: Int = 0
    var bottomRightRadius: Int = 0
    var bottomLeftRadius: Int = 0
    var strokeWidth: Int = 0
    var strokeColor: ColorStateList? = null
    var startColor: Int = 0
    var endColor: Int = 0
    var orientation: Int = 0

    constructor(builder: Builder) {
        this.corners = builder.corners
        this.solidColor = builder.solidColor
        this.strokeColor = builder.strokeColor
        this.strokeWidth = builder.strokeWidth
        this.topLeftRadius = builder.topLeftRadius
        this.topRightRadius = builder.topRightRadius
        this.bottomLeftRadius = builder.bottomLeftRadius
        this.bottomRightRadius = builder.bottomRightRadius
        this.startColor = builder.startColor
        this.endColor = builder.endColor
        this.orientation = builder.orientation
    }

    constructor(corners: Int, solidColor: ColorStateList) {
        this.corners = corners
        this.solidColor = solidColor
    }


    fun isEmpty(): Boolean {
        return corners == 0 && solidColor == null && topLeftRadius == 0 && topRightRadius == 0 && bottomRightRadius == 0
                && bottomLeftRadius == 0 && strokeWidth == 0 && strokeColor == null && startColor == 0 && endColor == 0
    }

    fun isCornerRadiusEmpty(): Boolean {
        return topLeftRadius == 0 && topRightRadius == 0 && bottomLeftRadius == 0 && bottomRightRadius == 0
    }


    class Builder {
        var corners: Int = 0
        var solidColor: ColorStateList? = null
        var topLeftRadius: Int = 0
        var topRightRadius: Int = 0
        var bottomRightRadius: Int = 0
        var bottomLeftRadius: Int = 0
        var strokeWidth: Int = 0
        var strokeColor: ColorStateList? = null
        var startColor: Int = 0
        var endColor: Int = 0
        var orientation: Int = 0


        fun setCorners(corners: Int): Builder {
            this.corners = corners
            return this
        }

        fun setSolidColor(color: ColorStateList?): Builder {
            this.solidColor = color
            return this
        }

        fun setSolidColor(color: Int): Builder {
            this.solidColor = ColorStateList.valueOf(color)
            return this
        }

        fun setStrokeWidth(width: Int): Builder {
            this.strokeWidth = width
            return this
        }

        fun setStrokeColor(color: ColorStateList?): Builder {
            this.strokeColor = color
            return this
        }

        fun setTopLeftRadius(radius: Int): Builder {
            this.topLeftRadius = radius
            return this
        }

        fun setTopRightRadius(radius: Int): Builder {
            this.topRightRadius = radius
            return this
        }

        fun setBottomLeftRadius(radius: Int): Builder {
            this.bottomLeftRadius = radius
            return this
        }

        fun setBottomRightRadius(radius: Int): Builder {
            this.bottomRightRadius = radius
            return this
        }

        fun setStartColor(color: Int): Builder {
            this.startColor = color
            return this
        }

        fun setEndColor(color: Int): Builder {
            this.endColor = color
            return this
        }

        fun setColorOrientation(orientation: Int): Builder{
            this.orientation = orientation
            return this
        }

        fun build(): Shape {
            return Shape(this)
        }

    }

}
