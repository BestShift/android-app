package com.example.bestshift_as.Kommfort;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.example.bestshift_as.R;

import java.util.ArrayList;
import java.util.List;


public class KammsherKreis extends Activity {

    SurfaceView surfview;
    float x;
    float y;
    int height=1080;
    int width=1920;
    List<Float> list = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kammsherkreis);
        RelativeLayout rl= (RelativeLayout) findViewById(R.id.rlkamm);

        rl.addView(new ChartView(this));
    }


}