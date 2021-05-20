package com.example.emetr;

public class Emetr {

    private final int GAIN_MAX = 7;

    private int gain = 1;
    private float toneArm = 0;
    private float tone = 0;
    private boolean autoSet = false;

    void setGain(int value){ if((value >= 0) && (value < GAIN_MAX)) gain = value; }
    int getGain(){ return gain; }
    void incGain(){ if(gain < GAIN_MAX) gain++; }
    void decGain(){ if(gain > 0) gain--; }
}
