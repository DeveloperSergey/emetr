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

        //------------------------------------------------------------------------------------------
        center_x = (int)(0 + radius + MARGIN);
        center_y = (int)(screenParam.height - radius - MARGIN);
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);

        // Background 1
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.WHITE);
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
        String s = String.valueOf(value);
        paint.getTextBounds(s, 0, s.length(), textBounds);
        canvas.drawText(s, center_x, center_y - textBounds.exactCenterY(), paint);


        //------------------------------------------------------------------------------------------
        center_x = (int)(screenParam.width - radius - MARGIN);
        center_y = (int)(screenParam.height - radius - MARGIN);
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);

        // Digits
        paint.setStrokeWidth(1);
        paint.setTextSize(70f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        final Rect textBounds2 = new Rect(); //don't new this up in a draw method
        s = "SET";
        paint.getTextBounds(s, 0, s.length(), textBounds2);
        canvas.drawText(s, center_x, center_y - textBounds2.exactCenterY(), paint);
    }
}
