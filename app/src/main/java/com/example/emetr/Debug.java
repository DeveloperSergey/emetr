package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Debug extends BasicView{

    private ArrayList<Integer> times = new ArrayList<>();
    private int time = 0;
    private long lastUpdateTime = 0;

    public void draw(Canvas canvas){
        paint.setStrokeWidth(1);
        paint.setTextSize(30f);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.BLACK);
        final Rect textBounds = new Rect(); //don't new this up in a draw method

        String str = "FPS: " + String.valueOf(1000 / ((time > 0) ? time : 1));
        paint.getTextBounds(String.valueOf(str), 0, String.valueOf(str).length(), textBounds);
        canvas.drawText(String.valueOf(str), screenParam.width - (textBounds.exactCenterX() * 2 + 50), 100 + (textBounds.exactCenterY()), paint);
    }

    public void setTime(int value){
        times.add(value);

        if((System.currentTimeMillis() - lastUpdateTime) > 1000){
            int sum = 0;
            for (int t : times){
                sum += t;
            }
            time = sum / times.size();
            times.clear();
            lastUpdateTime = System.currentTimeMillis();
        }
    }
}
