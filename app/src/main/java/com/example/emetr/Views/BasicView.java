package com.example.emetr.Views;

import android.graphics.Paint;

import com.example.emetr.Views.ScreenParam;

public class BasicView {

    Paint paint = new Paint();
    ScreenParam screenParam;
    final int MARGIN = 50;


    public void setScreenParam(ScreenParam screenParam){
        this.screenParam = screenParam;
    }
}
