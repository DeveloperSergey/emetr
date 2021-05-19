package com.example.emetr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ScaleView extends View implements View.OnTouchListener {

    private Context context;
    private Paint mPaint = new Paint();

    private int x0;
    private int y0;
    private int sweepAngle = 0;
    private int lineLength = 0;

    public ScaleView(Context context) {
        super(context);
        this.context = context;
        setOnTouchListener(this);

        /*this.setOnTouchListener(new SwipeListener(context){
            public void onSwipeTop() {
                Toast.makeText(context, "top: " + String.valueOf(dy), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(context, "right: " + String.valueOf(dx), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(context, "left: " + String.valueOf(dx), Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(context, "bottom: " + String.valueOf(dy), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = this.getWidth();
        int height = this.getHeight();

        // Fill style
        mPaint.setStyle(Paint.Style.FILL);

        // Fill canvas
        mPaint.setColor(Color.GRAY);
        canvas.drawPaint(mPaint);


        final RectF oval = new RectF();
        float radius = 100f;
        float center_x, center_y;
        center_x = (int)(width / 2);
        center_y = (int)(height / 2);
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);

        // Rectangle
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, width, height, mPaint);

        // Background 0
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawCircle(center_x, center_y, radius, mPaint);

        // Background 1
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(center_x, center_y, radius, mPaint);

        // Value
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(oval, 90, sweepAngle, false, mPaint); // рисуем пакмана

        // Digits
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(40f);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.WHITE);
        final Rect textBounds = new Rect(); //don't new this up in a draw method
        mPaint.getTextBounds(String.valueOf(sweepAngle), 0, String.valueOf(sweepAngle).length(), textBounds);
        canvas.drawText(String.valueOf(sweepAngle), center_x, center_y - textBounds.exactCenterY(), mPaint);

        // Line
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        canvas.drawLine(width, height-lineLength, width, height, mPaint);
        Log.d("ScaleView", String.valueOf(height-lineLength));
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        int width = this.getWidth();
        int height = this.getHeight();

        final int x = (int) event.getX();
        final int y = (int) event.getY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                // Click
                x0 = (int)event.getX();
                y0 = (int)event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                // Move
                int deltaX = x - x0;
                int deltaY = y - y0;
                if(Math.abs(deltaX) > 20){
                    x0 = x;
                    if(deltaX > 0) sweepAngle++;
                    else sweepAngle--;

                    if(sweepAngle < 0) sweepAngle = 0;
                    if(sweepAngle > 360) sweepAngle = 360;
                    this.invalidate();
                }
                if(Math.abs(deltaY) > 50){
                    y0 = y;
                    if(deltaY > 0) lineLength -= 10;
                    else lineLength += 10;

                    if(lineLength < 0) lineLength = 0;
                    if(lineLength > height) lineLength = height;
                    this.invalidate();
                }
                break;
        }
        return true;
    }
}
