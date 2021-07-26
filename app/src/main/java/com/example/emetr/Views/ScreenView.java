package com.example.emetr.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.emetr.BLE.Factory;

public class ScreenView extends View implements View.OnTouchListener{

    interface ScreenViewCallback{
        void setOffset(int offset);
    }

    final private Context context;
    final private ScreenViewCallback screenViewCallback;
    final private Debug debug = new Debug();
    final private Paint mPaint = new Paint();
    final private Arrow arrow = new Arrow();
    final private Gain gain = new Gain();
    final private ToneArm toneArm = new ToneArm();
    final private Scale scale = new Scale();
    final private ScreenParam screenParam = new ScreenParam();

    private int angleOffset = 0;
    private final int GAIN_NUM = 8;
    private int x;
    private int y;
    public float angle = 0;
    private int gainLinLength = 0;
    private boolean connected = false;

    private boolean single = true;
    private long timeLastUpdate = 0;

    public ScreenView(Context context, ScreenViewCallback screenViewCallback) {
        super(context);
        this.context = context;
        setOnTouchListener(this);
        Log.d("ScaleView", "w: " + String.valueOf(getWidth()));
        this.screenViewCallback = screenViewCallback;
    }

    public void redraw(){
        this.invalidate();
    }

    public void setConnected(boolean state){
        this.connected = state;
        redraw();
    }

    @Override
    protected void onDraw(Canvas canvas) {

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
        if(connected) mPaint.setColor(Color.GRAY);
        else mPaint.setColor(Color.DKGRAY);
        canvas.drawPaint(mPaint);

        // Area
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, width, height, mPaint);

        // Views
        scale.draw(canvas);
        toneArm.draw(canvas, (int) angle);
        gain.draw(canvas, gainLinLength);
        arrow.draw(canvas, angle + angleOffset);
        debug.draw(canvas);

        // FPS
        long time = System.currentTimeMillis();
        Log.d("ScreenView", "Draw time [ms]: " + String.valueOf(time - timeLastUpdate));
        debug.setTime((int)(time - timeLastUpdate));
        timeLastUpdate = time;
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
                this.x = (int) event.getX();
                this.y = (int) event.getY();
                if((this.x > (width - 200)) && (this.y > (height - 200))){
                    angleOffset = (int) (screenParam.SET_ANGLE_VALUE - angle);
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
                int deltaX = x - this.x;
                int deltaY = y - this.y;
                if ((Math.abs(deltaY) > 200) && (this.x < width / 5)) {
                    this.y = y;
                    if (deltaY > 0) gainLinLength--;
                    else gainLinLength++;

                    if (gainLinLength < 0) gainLinLength = 0;
                    if (gainLinLength >= GAIN_NUM) gainLinLength = GAIN_NUM - 1;
                    this.invalidate();
                } else if (Math.abs(deltaX) > 20) {
                    this.x = x;
                    if (deltaX > 0) angleOffset++;
                    else angleOffset--;

                    if (angleOffset < -180) angleOffset = -180;
                    if (angleOffset > 180) angleOffset = 180;
                    this.invalidate();
                }
                break;
        }
        return true;
    }
}