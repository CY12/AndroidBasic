package com.example.space.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {
    Paint paint = new Paint();

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 2000,Color.GREEN, Color.WHITE, Shader.TileMode.CLAMP);
//        paint.setShader(linearGradient);//目标Paint即为绘制柱的Paint

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(canvas.getWidth()/2, 200); //将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, 100, paint); //画圆圈
        //使用path绘制路径文字
        canvas.save();
        canvas.translate(-75, -75);
        Path path = new Path();
        path.addArc(new RectF(0,0,150,150), -180, 180);
        Paint citePaint = new Paint(paint);
        citePaint.setTextSize(14);
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("http://www.android777.com", path, 28, 0, citePaint);
        canvas.restore();
        Paint tmpPaint = new Paint(paint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(1);
        float  y=100;
        int count = 60; //总刻度数
        for(int i=0 ; i <count ; i++){
            if(i%5 == 0){
                canvas.drawLine(0f, y, 0, y+12f, paint);
                canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);
            }else{
                canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
            }
            canvas.rotate(360/count,0f,0f); //旋转画纸
        }
        //绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        canvas.drawLine(0, 10, 0, -65, paint);
    }
}
