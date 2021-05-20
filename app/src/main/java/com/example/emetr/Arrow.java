package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Arrow extends BasicView{

    final int MENU_HEIGHT = 0;
    final int SHIFT_CENTER = 0;

    public void draw(Canvas canvas, int value){

        if(value < screenParam.MIN_ANGLE) value = screenParam.MIN_ANGLE;
        if(value > (180-screenParam.MIN_ANGLE)) value = (180-screenParam.MIN_ANGLE);
        int radius = screenParam.height - screenParam.SCALE_HEIGHT;
        //int g  = (int) ((SHIFT_CENTER + MENU_HEIGHT) / Math.sin(value * 314 / 18000.0));

        int g = screenParam.height - radius; // (height - arrow length)
        Log.d("Arrow", "g: " + String.valueOf(g));

        int x0 = (int)(g * Math.cos(value * 314 / 18000.0));
        int y0 = (int)(g * Math.sin(value * 314 / 18000.0));
        int x1 = (int)(screenParam.height * Math.cos(value * 314 / 18000.0));
        int y1 = (int)(screenParam.height * Math.sin(value * 314 / 18000.0));

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        canvas.drawLine(x0+ screenParam.width/2, screenParam.height-y0,
                x1+ screenParam.width/2, screenParam.height-y1, paint);
    }
}
