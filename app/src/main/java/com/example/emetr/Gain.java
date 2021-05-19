package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Gain {

    private Paint paint = new Paint();
    private final int GAIN_NUM = 8;

    final int gain[] = { 1,2,4,8,16,32,64,128 };    // 8
    int bg_width = 15;
    int val_width = 10;

    public void draw(Canvas canvas, int width, int height, int value){

        paint.setStrokeWidth(bg_width);
        canvas.drawLine(bg_width/2, 0, bg_width/2, height, paint);

        // Value
        paint.setStrokeWidth(val_width);
        paint.setColor(Color.GREEN);
        canvas.drawLine(bg_width/2, height - ((value) * (height / (GAIN_NUM-1))), bg_width/2, height, paint);
        Log.d("ScaleView", String.valueOf(value));

        // Digits
        paint.setStrokeWidth(1);
        paint.setTextSize(40f);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.WHITE);
        final Rect textBounds = new Rect(); //don't new this up in a draw method
        paint.getTextBounds(String.valueOf(gain[value]), 0, String.valueOf(gain[value]).length(), textBounds);
        canvas.drawText(String.valueOf(gain[value]), bg_width, height + (textBounds.exactCenterY()), paint);
    }
}
