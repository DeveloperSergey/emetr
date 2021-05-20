package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Scale extends BasicView{

    public void  draw(Canvas canvas, int width, int height){

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);

        int radius = width/2 - MARGIN;
        int center_x = (int)(width/2);
        int center_y = (int)(height/2) + radius/2;
        final RectF oval = new RectF();
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        canvas.drawArc(oval, 225, 90, false, paint); // рисуем пакмана
    }
}
