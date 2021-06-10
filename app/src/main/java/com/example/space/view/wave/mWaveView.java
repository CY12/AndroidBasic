package com.example.space.view.wave;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.space.R;

public class mWaveView extends View {

    private int color;
    private int secondColor;
    private int bgColor = Color.GRAY;
    private int defaultSize ;
    private int viewSize;
    private int waveNum;
    private int waveWidth;
    private int waveHeight;
    private Paint wavePaint;
    private Paint secondWavePaint;
    private Paint circlePaint;

    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    private Path wavePath;
    private float moveDistance;

    private float percent;

    ValueAnimator valueAnimator;

    public mWaveView(Context context) {
        super(context);
        init(context);
    }

    public mWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        defaultSize = dip2px(context,200);
        wavePath = new Path();
        wavePaint = new Paint();
        color = getResources().getColor(R.color.color_3);
        secondColor = getResources().getColor(R.color.color_4);



        wavePaint.setColor(color);
        wavePaint.setAntiAlias(true);//设置抗锯齿
        wavePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        secondWavePaint = new Paint();
        secondWavePaint.setColor(secondColor);
        secondWavePaint.setAntiAlias(true);//设置抗锯齿
        secondWavePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));//因为要覆盖在第一层波浪上，且要让半透明生效，所以选此模式

        circlePaint = new Paint();
        circlePaint.setColor(bgColor);
        circlePaint.setAntiAlias(true);//设置抗锯齿


        waveHeight = dip2px(context,8);
        waveWidth = dip2px(context,48);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = measureSize(defaultSize, heightMeasureSpec);
        int width = measureSize(defaultSize, widthMeasureSpec);
        Log.d("Test","result   height=="+height+" width=="+width);
        int min = Math.min(width, height);// 获取View最短边的长度
        setMeasuredDimension(min, min);// 强制改View为以最短边为长度的正方形
        viewSize = min;
        Log.d("Test","ViewSize==="+viewSize+"  waveWidth=="+waveWidth);
        waveNum =(int) Math.ceil(viewSize/waveWidth*2);
        Log.d("Test","num"+waveNum);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        bitmap = Bitmap.createBitmap(viewSize, viewSize, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        Log.d("Test","onDraw"+" viewSize"+viewSize);
        bitmapCanvas.drawCircle(viewSize/2, viewSize/2, viewSize/2, circlePaint);
        bitmapCanvas.drawPath(getWavePath(),wavePaint);
        bitmapCanvas.drawPath(getSecondWavePath(),secondWavePaint);
//        if(isDrawSecondWave){
//            bitmapCanvas.drawPath(getSecondWavePath(),secondWavePaint);
//        }

        canvas.drawBitmap(bitmap, 0, 0, null);
//        canvas.drawPath(getWavePath(),wavePaint);

    }

    private Path getWavePath(){
        wavePath.reset();

        //移动到右上方，也就是p0点
        wavePath.moveTo(viewSize, viewSize*percent);
        //移动到右下方，也就是p1点
        wavePath.lineTo(viewSize, viewSize);
        //移动到左下边，也就是p2点
        wavePath.lineTo(0, viewSize);
        //移动到左上方，也就是p3点
        //wavePath.lineTo(0, (1-percent)*viewSize);
        wavePath.lineTo(-moveDistance*percent, viewSize*percent);

        //从p3开始向p0方向绘制波浪曲线
        for (int i=0;i<waveNum;i++){
            wavePath.rQuadTo(waveWidth/2, waveHeight, waveWidth, 0);
            wavePath.rQuadTo(waveWidth/2, -waveHeight, waveWidth, 0);
        }

        //将path封闭起来
        wavePath.close();
        return wavePath;
    }

    private Path getSecondWavePath(){
        wavePath.reset();

        //移动到左上方，也就是p3点
        wavePath.moveTo(0, viewSize*percent);
        //移动到左下边，也就是p2点
        wavePath.lineTo(0, viewSize);
        //移动到右下方，也就是p1点
        wavePath.lineTo(viewSize, viewSize);
        //移动到右上方，也就是p0点
        wavePath.lineTo(viewSize+moveDistance*percent,viewSize*percent);

        //从p0开始向p3方向绘制波浪曲线
        for (int i=0;i<waveNum;i++){
            wavePath.rQuadTo(-waveWidth/2, waveHeight, -waveWidth, 0);
            wavePath.rQuadTo(-waveWidth/2, -waveHeight, -waveWidth, 0);
        }

        //将path封闭起来
        wavePath.close();
        return wavePath;
    }






    private int measureSize(int defaultSize,int measureSpec) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {

            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {

            result = Math.min(result, specSize);
        }

        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void startAnimator(int time,float stop){


        valueAnimator = ValueAnimator.ofFloat(0,stop);
        valueAnimator.setDuration(time);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        //valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {


                if (percent < stop){
                    percent =animation.getAnimatedFraction();
                }

                moveDistance = 2*waveWidth/stop*animation.getAnimatedFraction();
                Log.d("Test","percent:"+percent);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    public void stopAnimator(){
        valueAnimator.end();
    }
}


