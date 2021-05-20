package com.example.emetr;

import android.graphics.Paint;

public class BasicView {

    Paint paint = new Paint();
    ScreenParam screenParam;
    final int MARGIN = 50;


    public void setScreenParam(ScreenParam screenParam){
        this.screenParam = screenParam;
    }
}
