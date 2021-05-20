package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Scale extends BasicView{

    public void draw(Canvas canvas){

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);

        int radius = screenParam.height;
        int center_x = (int)(screenParam.width/2);
        int center_y = (int)(screenParam.height);
        final RectF oval = new RectF();
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);

        int startAngle = 180 + screenParam.MIN_ANGLE;
        int angle = 180 - (2 * screenParam.MIN_ANGLE);

        paint.setStrokeWidth(50);
        paint.setColor(Color.DKGRAY);
        canvas.drawArc(oval, startAngle, angle, false, paint);

        paint.setStrokeWidth(10);
        paint.setColor(Color.GRAY);
        canvas.drawArc(oval, startAngle, angle, false, paint);

        paint.setStrokeWidth(1);
        paint.setColor(Color.LTGRAY);
        canvas.drawArc(oval, startAngle, angle, false, paint);
    }

    private void drawSector(Canvas canvas, int startAngle, int stopAngle){

    }
}
