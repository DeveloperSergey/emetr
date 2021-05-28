package com.example.emetr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ScreenView extends View implements View.OnTouchListener{

    final private Context context;
    final private Debug debug = new Debug();
    final private Paint mPaint = new Paint();
    final private Arrow arrow = new Arrow();
    final private Gain gain = new Gain();
    final private ToneArm toneArm = new ToneArm();
    final private Scale scale = new Scale();
    final private ScreenParam screenParam = new ScreenParam();

    private final int GAIN_NUM = 8;
    private int x0, x1;
    private int y0, y1;
    private int sweepAngle = 0;
    private int lineLength = 0;

    private boolean single = true;

    public ScreenView(Context context) {
        super(context);
        this.context = context;
        setOnTouchListener(this);
        Log.d("ScaleView", "w: " + String.valueOf(getWidth()));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        long time[] = { 0, 0 };
        time[0] = System.currentTimeMillis();

        super.onDraw(canvas);
        final int MARGIN = 50;
        int width = this.getWidth();
        int height = this.getHeight();

        if(single){
            single = false;
            screenParam.setParams(width, height);
            debug.setScreenParam(screenParam);
            arrow.setScreenParam(screenParam);
            gain.setScreenParam(screenParam);
            scale.setScreenParam(screenParam);
            toneArm.setScreenParam(screenParam);
        }

        // Fill canvas
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
        canvas.drawPaint(mPaint);

        // Rectangle - Area
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, width, height, mPaint);

        // Views
        scale.draw(canvas);
        toneArm.draw(canvas, sweepAngle);
        gain.draw(canvas, lineLength);
        arrow.draw(canvas, sweepAngle);
        debug.draw(canvas);


        time[1] = System.currentTimeMillis();
        Log.d("ScaleView", "Draw time [ms]: " + String.valueOf(time[1] - time[0]));
        debug.setTime((int)(time[1] - time[0]));
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
                x0 = (int) event.getX();
                y0 = (int) event.getY();
                if((x0 > (width - 200)) && (y0 > (height - 200))){
                    sweepAngle = screenParam.SET_ANGLE_VALUE;
                    this.invalidate();
                }
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
                if ((Math.abs(deltaY) > 100) && (x0 < width / 5)) {
                    y0 = y;
                    if (deltaY > 0) lineLength--;
                    else lineLength++;

                    if (lineLength < 0) lineLength = 0;
                    if (lineLength >= GAIN_NUM) lineLength = GAIN_NUM - 1;
                    this.invalidate();
                } else if (Math.abs(deltaX) > 20) {
                    x0 = x;
                    if (deltaX > 0) sweepAngle++;
                    else sweepAngle--;

                    if (sweepAngle < 0) sweepAngle = 0;
                    if (sweepAngle > 270) sweepAngle = 270;
                    this.invalidate();
                }
                break;
        }
        return true;
    }
}
