package com.example.emetr;

import android.util.Log;

import com.example.emetr.BLE.Factory;
import com.example.emetr.Views.ScreenView;

public class Emetr {

    private final ScreenView view;

    Emetr(ScreenView view){
        this.view = view;
    }

    private final int GAIN_MAX = 7;

    private boolean connectionState = false;
    private int gain = 1;
    private float toneArm = 0;
    private float toneValue = 0;

    Factory factory;

    void setFactory(Factory factory){
        this.factory = factory;
    }

    void setGain(int value){ if((value >= 0) && (value < GAIN_MAX)) gain = value; }
    int getGain(){ return gain; }

    void setToneValue(float value){
        this.toneValue = value;
        view.angle = getAngle();
        view.redraw();
    }

    void setToneArm(float value){
        this.toneArm = value;
    }
    float getToneArm(){
        return this.toneArm;
    }

    void setConnection(boolean state){
        connectionState = state;
        view.setConnected(state);
    }
    boolean getConnection(){
        return connectionState;
    }

    float getAngle(){
        float value = (float)180.0 - (float)(180.0 / 65536 * (32768 + toneValue));
        Log.d("Emetr", "value: " + String.valueOf(value));
        return value;
    }
}