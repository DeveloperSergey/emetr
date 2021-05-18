package com.example.emetr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ScaleView extends View implements View.OnTouchListener {

    private Context context;
    private Paint mPaint = new Paint();

    private int x0;
    private int y0;
    private int sweepAngle = 0;

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

        // Fill style
        mPaint.setStyle(Paint.Style.FILL);

        // Fill canvas
        mPaint.setColor(Color.GRAY);
        canvas.drawPaint(mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        final RectF oval = new RectF();
        float radius = 100f;
        float center_x, center_y;
        center_x = 240;
        center_y = 220;
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        canvas.drawArc(oval, 90, sweepAngle, true, mPaint); // рисуем пакмана
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

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
                int delta = x - x0;
                if(Math.abs(delta) > 20){
                    x0 = x;
                    if(delta > 0) sweepAngle++;
                    else sweepAngle--;
                    if(sweepAngle < 0) sweepAngle = 0;
                    if(sweepAngle > 360) sweepAngle = 360;
                    //Toast.makeText(context, String.valueOf(delta), Toast.LENGTH_LONG).show();
                    Log.d("ScaleView", String.valueOf(delta > 0));
                    this.invalidate();
                }
                break;
        }
        return true;
    }
}
