package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class ToneArm extends BasicView{

    public void draw(Canvas canvas, int value){
        final RectF oval = new RectF();
        float radius = 100f;
        float center_x, center_y;
        center_x = (int)(0 + radius + MARGIN);
        center_y = (int)(screenParam.height - radius - MARGIN);
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);

        /*/ Background 0
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(15);
        paint.setColor(Color.DKGRAY);
        canvas.drawCircle(center_x, center_y, radius, paint);*/

        // Background 1
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.WHITE);
        //canvas.drawCircle(center_x, center_y, radius, paint);
        canvas.drawArc(oval, 135, 270, false, paint); // рисуем пакмана

        // Value
        paint.setStrokeWidth(15);
        paint.setColor(Color.GREEN);
        canvas.drawArc(oval, 135, value, false, paint); // рисуем пакмана

        // Digits
        paint.setStrokeWidth(1);
        paint.setTextSize(70f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        final Rect textBounds = new Rect(); //don't new this up in a draw method
        paint.getTextBounds(String.valueOf(value), 0, String.valueOf(value).length(), textBounds);
        canvas.drawText(String.valueOf(value), center_x, center_y - textBounds.exactCenterY(), paint);
    }
}
