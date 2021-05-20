package com.example.emetr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ScaleView extends View implements View.OnTouchListener {

    final private Context context;
    final private Paint mPaint = new Paint();
    final private Arrow arrow = new Arrow();
    final private Gain gain = new Gain();
    final private  ToneArm toneArm = new ToneArm();
    final private Scale scale = new Scale();
    final private ScaleParam scaleParam = new ScaleParam();

    private final int GAIN_NUM = 8;
    private int x0;
    private int y0;
    private int sweepAngle = 0;
    private int lineLength = 0;

    private boolean single = true;

    public ScaleView(Context context) {
        super(context);
        this.context = context;
        setOnTouchListener(this);

        Log.d("ScaleView", "w: " + String.valueOf(getWidth()));

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
        final int MARGIN = 50;
        int width = this.getWidth();
        int height = this.getHeight();

        if(single){
            single = false;
            scaleParam.setParams(width, height);
            arrow.setScaleParam(scaleParam);
            gain.setScaleParam(scaleParam);
            scale.setScaleParam(scaleParam);
            toneArm.setScaleParam(scaleParam);
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


        //------------------------------------------------------------------------------------------
        // Tone arm
        toneArm.draw(canvas, width, height, sweepAngle);
        //------------------------------------------------------------------------------------------


        //------------------------------------------------------------------------------------------
        // Sensitive
        gain.draw(canvas, width, height, lineLength);
        //------------------------------------------------------------------------------------------


        //------------------------------------------------------------------------------------------
        // Scale
        scale.draw(canvas, width, height);
        //------------------------------------------------------------------------------------------


        //------------------------------------------------------------------------------------------
        // Arrow
        arrow.draw(canvas, width, height, sweepAngle);
        //------------------------------------------------------------------------------------------
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
                if((Math.abs(deltaY) > 100) && (x0 < width / 5)){
                    y0 = y;
                    if(deltaY > 0) lineLength --;
                    else lineLength ++;

                    if(lineLength < 0) lineLength = 0;
                    if(lineLength >= GAIN_NUM) lineLength = GAIN_NUM - 1;
                    this.invalidate();
                }
                else if(Math.abs(deltaX) > 20){
                    x0 = x;
                    if(deltaX > 0) sweepAngle++;
                    else sweepAngle--;

                    if(sweepAngle < 0) sweepAngle = 0;
                    if(sweepAngle > 270) sweepAngle = 270;
                    this.invalidate();
                }
                break;
        }
        return true;
    }
}
