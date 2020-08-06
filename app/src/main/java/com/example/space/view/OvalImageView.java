package com.example.space.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.space.R;




/*
 *用来显示不规则图片，
 * 上面两个是圆角，下面两个是直角
 * */
public class OvalImageView extends AppCompatImageView {
    /*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    //此处可根据自己需要修改大小
    private int rx;
    private int ry;


    public OvalImageView(Context context) {
        super(context);
        init(context,null);

    }


    public OvalImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    public OvalImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs){
        if (attrs==null){
            return;
        }
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.OvalImageView);
        rx=(int)a.getDimension(R.styleable.OvalImageView_Oval_rx,dp2px(context,10));
        ry=(int)a.getDimension(R.styleable.OvalImageView_Oval_ry,(int)dp2px(context,10));
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    /**
     * 画图
     *
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        /*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        path.addRoundRect(new RectF(0, 0, w, h), rx,ry, Path.Direction.CW);

        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    private float dp2px(Context context, float dp) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}