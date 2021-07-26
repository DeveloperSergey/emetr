package com.example.emetr.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.emetr.Views.BasicView;

public class Gain extends BasicView {

    private final int GAIN_NUM = 8;
    private final int SPACE_SIZE = 5;
    final int gain[] = { 1,2,4,8,16,32,64,128 };    // 8
    int bg_width = 20;
    int val_width = 15;
    final String [] colors = {
            "#FF0000", "#ffbf00", "#ffff00", "#40ff00", "#00ffff", "#0000ff", "#bf00ff"
    };

    public void draw(Canvas canvas, int value){

        // Background
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(bg_width);
        canvas.drawLine(bg_width/2, 0, bg_width/2, screenParam.height, paint);

        // Value
        paint.setStrokeWidth(val_width);
        paint.setColor(Color.DKGRAY);

        for(int i = 0; i < value; i++) {
            int y1 = screenParam.height - (i * (screenParam.height / (GAIN_NUM - 1))) - SPACE_SIZE;
            int y2 = screenParam.height - ((i+1) * (screenParam.height / (GAIN_NUM - 1))) + SPACE_SIZE;
            if(i == (value - 1)) paint.setColor(Color.parseColor(colors[i]));
            canvas.drawLine(bg_width / 2, y1,
                            bg_width / 2, y2,
                            paint);
        }
        Log.d("ScaleView", String.valueOf(value));

        // Text
        paint.setStrokeWidth(1);
        paint.setTextSize(48f);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.DKGRAY);
        final Rect textBounds = new Rect(); //don't new this up in a draw method
        paint.getTextBounds(String.valueOf(gain[value]), 0, String.valueOf(gain[value]).length(), textBounds);
        canvas.drawText(String.valueOf(gain[value]), bg_width + SPACE_SIZE, screenParam.height + (textBounds.exactCenterY()), paint);
    }
}
