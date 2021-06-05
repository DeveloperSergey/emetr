package com.example.emetr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

        // Background
        paint.setStrokeWidth(50);
        paint.setColor(Color.LTGRAY);
        paint.setShadowLayer(2f, 0f, 5f, Color.DKGRAY);
        canvas.drawArc(oval, startAngle, angle, false, paint);
        paint.reset();

        paint.setStyle(Paint.Style.STROKE);

        // Set
        paint.setStrokeWidth(100);
        paint.setColor(Color.GREEN);
        canvas.drawArc(oval, startAngle + (180 - screenParam.SET_ANGLE_VALUE) - 0.5f, 1.0f, false, paint);

        // Big
        paint.setStrokeWidth(50);
        paint.setColor(Color.DKGRAY);
        int numOfSection = screenParam.SCALE_BIG_SECTION_NUM;
        float arcStart, arcLength;
        for(int i = 0; i < (numOfSection); i++) {
            arcStart = startAngle + ((angle / numOfSection) * i);
            canvas.drawArc(oval, arcStart, (float) 0.2, false, paint);
        }

        // Little
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(25);
        radius = screenParam.height - 37;
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        numOfSection = screenParam.SCALE_SMALL_SECTION_NUM;
        for(int i = 0; i < (numOfSection); i++) {
            arcStart = startAngle + ((angle / numOfSection) * i);
            canvas.drawArc(oval, arcStart, (float) 0.2, false, paint);
        }

        // Text
        radius -= 80;
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        paint.setStrokeWidth(3);
        paint.setTextSize(48f);
        Path arc = new Path();
        arc.addArc(oval, startAngle + 70 - 15, 50);
        canvas.drawTextOnPath("RISE", arc, 0, 0, paint);

        arc.reset();
        arc.addArc(oval, startAngle + 70 + 5, 50);
        canvas.drawTextOnPath("FALL", arc, 0, 0, paint);
    }
}
