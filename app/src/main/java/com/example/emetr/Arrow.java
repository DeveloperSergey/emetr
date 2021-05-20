package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class Arrow extends BasicView{

    public void draw(Canvas canvas, int width, int height, int value){

        final int MENU_HEIGHT = 0;
        final int SHIFT_CENTER = 0;
        int g  = (int) ((SHIFT_CENTER + MENU_HEIGHT) / Math.sin(value * 314 / 18000.0));
        Log.d("Arrow", "g: " + String.valueOf(g));
        int x0 = (int)(g * Math.cos(value * 314 / 18000.0));
        int y0 = (int)(g * Math.sin(value * 314 / 18000.0));
        int x1 = (int)(height * Math.cos(value * 314 / 18000.0));
        int y1 = (int)(height * Math.sin(value * 314 / 18000.0));

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        canvas.drawLine(x0+width/2, height-y0, x1+width/2, height-y1, paint);
    }
}
