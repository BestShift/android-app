package com.example.bestshift_as.Kommfort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fitim on 10.03.2016.
 */
public class ChartView extends View {
    float phi;
    float y;
    float x;
    float radius=400;
    float radiusgeneriert;
    List<Float> xvalues = new ArrayList<Float>();
    List<Float> yvalues = new ArrayList<Float>();
    List<Float> mockdaten = new ArrayList<Float>();


    // CONSTRUCTOR
    public ChartView(Context context) {
        super(context);
        setFocusable(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mockdaten.add((float) -50.00);
        mockdaten.add((float) -100.00);
        mockdaten.add((float) -150.00);
        mockdaten.add((float) -190.00);
        mockdaten.add((float) -250.00);
        mockdaten.add((float) -150.00);

        int w = getWidth() / 2;
        int h = getHeight() / 2;
        Paint drawblack = new Paint();
        drawblack.setColor(Color.BLACK);
        drawblack.setStyle(Paint.Style.STROKE);
        drawblack.setStyle(Paint.Style.FILL);
        Paint p = new Paint();
        // smooths
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(4.5f);
        // opacity
        //p.setAlpha(0x80); //
        phi = (float) (Math.random() * (270  + 1) +0);
        radiusgeneriert=(float) (Math.random() * (radius  + 1) +0);

        x= (float) (radiusgeneriert*Math.cos(phi));
        y= (float) (radiusgeneriert*Math.sin(phi));
        xvalues.add(x);
        yvalues.add(y);
        for (int i = 0; i < yvalues.size(); i++) {
            System.out.println("y:" +yvalues.get(i));
            System.out.println("x:" +xvalues.get(i));
        }
        xvalues.toArray();
        yvalues.toArray();
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, p);
        canvas.drawCircle((getWidth() / 2) + x, (getHeight() / 2) + y, 10, drawblack);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
        postInvalidate();

        for (int i = 0; i < xvalues.size(); i++) {
            p.setColor(Color.BLACK);
            p.setStyle(Paint.Style.FILL);
            canvas.drawCircle((getWidth() / 2) + xvalues.get(i), (getHeight() / 2) + yvalues.get(i), 10, p);
            invalidate();
            postInvalidate();
            if (xvalues.get(i) == null) {
                break;
            }

        }


    }
}
