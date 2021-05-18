package com.example.emetr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ScaleView scaleView = new ScaleView(getApplicationContext(), size);
        setContentView(scaleView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        Log.d("emetr_activity", "App started.");
    }
}