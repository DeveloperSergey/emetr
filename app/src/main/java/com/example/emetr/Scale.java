package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Scale extends BasicView{

    public void draw(Canvas canvas){

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);

        int radius = screenParam.height - 25;
        int center_x = (int)(screenParam.width/2);
        int center_y = (int)(screenParam.height);
        final RectF oval = new RectF();
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);

        float startAngle = 180 + screenParam.MIN_ANGLE;
        float angle = 180 - (2 * screenParam.MIN_ANGLE);

        paint.setStrokeWidth(50);
        paint.setColor(Color.LTGRAY);
        canvas.drawArc(oval, startAngle, angle, false, paint);

        paint.setColor(Color.DKGRAY);

        // Big
        int numOfSection = 20;
        float arcStart, arcLength;
        for(int i = 0; i < (numOfSection); i++) {
            arcStart = startAngle + ((angle / numOfSection) * i);
            canvas.drawArc(oval, arcStart, (float) 0.2, false, paint);
        }

        // Little
        paint.setStrokeWidth(25);
        radius = screenParam.height - 37;
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        numOfSection = 100;
        for(int i = 0; i < (numOfSection); i++) {
            arcStart = startAngle + ((angle / numOfSection) * i);
            canvas.drawArc(oval, arcStart, (float) 0.2, false, paint);
        }
    }
}
