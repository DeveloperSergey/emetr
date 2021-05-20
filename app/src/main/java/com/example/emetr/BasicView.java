package com.example.emetr;

import android.content.Context;
import android.graphics.Paint;

public class BasicView {

    Context context;
    Paint paint = new Paint();
    ScaleParam scaleParam;
    final int MARGIN = 50;

    public void setScaleParam(ScaleParam scaleParam){
        this.scaleParam = scaleParam;
    }
}
