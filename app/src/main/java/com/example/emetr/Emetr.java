package com.example.emetr;

import android.util.Log;

public class Emetr {

    private final ScreenView view;

    Emetr(ScreenView view){
        this.view = view;
    }

    private final int GAIN_MAX = 7;

    private int gain = 1;
    private float toneArm = 0;
    private float tone = 0;
    private boolean autoSet = false;

    void setGain(int value){ if((value >= 0) && (value < GAIN_MAX)) gain = value; }
    int getGain(){ return gain; }
    void incGain(){ if(gain < GAIN_MAX) gain++; }
    void decGain(){ if(gain > 0) gain--; }

    void setTone(float value){
        this.tone = value;
        //Log.d("Emetr", "Set tone: " + String.valueOf(value));
        view.sweepAngle = getAngle();
        view.redraw();
    }

    float getAngle(){
        float value = (float)(180.0 / 32768 * tone);
        Log.d("Emetr", "value: " + String.valueOf(value));
        return value;
    }
}
