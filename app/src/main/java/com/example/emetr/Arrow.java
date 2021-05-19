package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Arrow {

    private Paint paint = new Paint();

    public void draw(Canvas canvas, int width, int height, int value){

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        canvas.drawLine(width/2, height, width/2, 0, paint);
    }
}
