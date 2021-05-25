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

        int startAngle = 180 + screenParam.MIN_ANGLE;
        int angle = 180 - (2 * screenParam.MIN_ANGLE);

        paint.setStrokeWidth(50);
        paint.setColor(Color.LTGRAY);
        canvas.drawArc(oval, startAngle, angle, false, paint);

        // Big
        paint.setColor(Color.DKGRAY);
        int arcNum = 20;
        int arcStart, arcLength;
        for(int i = 0; i < (arcNum); i++) {
            arcStart = startAngle + (angle / arcNum) * i;
            canvas.drawArc(oval, arcStart, (float) 0.2, false, paint);
        }

        // Little
        paint.setStrokeWidth(25);
        radius = screenParam.height - 37;
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        arcNum = 100;
        for(int i = 0; i < (arcNum); i++) {
            arcStart = startAngle + (angle / arcNum) * i;
            canvas.drawArc(oval, arcStart, (float) 0.2, false, paint);
        }

        paint.setStrokeWidth(1);
        canvas.drawArc(oval, startAngle, angle, false, paint);
    }

    private void drawSector(Canvas canvas, int startAngle, int stopAngle){

    }
}
