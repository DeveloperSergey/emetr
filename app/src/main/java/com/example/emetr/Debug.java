package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Debug extends BasicView{

    private int time = 0;

    public void draw(Canvas canvas){
        paint.setStrokeWidth(1);
        paint.setTextSize(30f);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.BLACK);
        final Rect textBounds = new Rect(); //don't new this up in a draw method

        String str = "FPS min: " + String.valueOf(1000 / ((time > 0) ? time : 1));
        paint.getTextBounds(String.valueOf(str), 0, String.valueOf(str).length(), textBounds);
        canvas.drawText(String.valueOf(str), screenParam.width - (textBounds.exactCenterX() * 2 + 50), 100 + (textBounds.exactCenterY()), paint);
    }

    public void setTime(int value){
        if(value > time) time = value;
    }
}
