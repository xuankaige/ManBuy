package com.bawei.gekaixuan.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by 葛凯旋 on 2017/9/19.
 */

public class Person extends ViewGroup {

    public Person(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        Log.e("onLayout","onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("onMeasure","onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Paint paint=new Paint();
        canvas.drawCircle(10,10,5,paint);
        Log.e("onDraw","onDraw");
    }
}
