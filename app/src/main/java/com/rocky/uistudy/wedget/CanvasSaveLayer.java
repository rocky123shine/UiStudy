package com.rocky.uistudy.wedget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.rocky.uistudy.R;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/10
 *     des:学习saveLayer
 * </pre>
 */
public class CanvasSaveLayer extends View {

    private int measureWidth;//父布局宽度
    private int measureHeight;//父布局高度
    private int OFFSET = 100;//偏移量
    private Paint mPaint;
    private Bitmap bitmap;
    private BitmapShader mShader;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        //设置画笔的颜色
        mPaint.setColor(Color.RED);
        //设置画笔的样式
        mPaint.setStyle(Paint.Style.FILL);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img);
        mShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);
    }

    public CanvasSaveLayer(Context context) {
        super(context);
    }

    public CanvasSaveLayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasSaveLayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.CYAN);
//        int saveCount = canvas.saveLayer(OFFSET, OFFSET, measureWidth - OFFSET, measureHeight - OFFSET, mPaint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawColor(Color.parseColor("#f0ffcc"));
//        int saveCount1 = canvas.saveLayer(OFFSET*2,OFFSET*2,measureWidth - OFFSET*2,measureHeight - OFFSET*2,mPaint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawColor(Color.parseColor("#f0cccc"));
//        canvas.restoreToCount(saveCount1);
//        canvas.drawCircle(300, 300, 650, mPaint);
//        canvas.restoreToCount(10);
//        float[] matrax = new float[]
//                {1, 0, 0, 0, 0,
//                        0, 0, 0, 0, 0,
//                        0, 0, 0, 0, 0,
//                        0, 0, 0, 1, 0,
//                };
//        mPaint.setColor(Color.BLUE);
//        mPaint.setColorFilter(new ColorMatrixColorFilter(matrax));
//        canvas.drawCircle(300, 300, 300, mPaint);

      //  canvas.drawCircle(200,200,200,mPaint);

    }
}
