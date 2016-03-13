package com.example.bestshift_as.Kommfort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hüseyin on 11.03.2016.
 */
public class ChartViewKomm extends View {
    float x;
    float y;
    List<Float> xvalues = new ArrayList<Float>();
    List<Float> yvalues = new ArrayList<Float>();

    // CONSTRUCTOR
    public ChartViewKomm(Context context) {
        super(context);
        setFocusable(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w=getWidth()/2;
        int h=getHeight()/2;
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
        x = (float) (Math.random() * (300+300+1)-250);
        y = (float) (Math.random() * (300+300+1)-250);
        xvalues.add(x);
        yvalues.add(y);
        for(int i=0; i<yvalues.size(); i++){
            System.out.println(yvalues.get(i));
        }
        xvalues.toArray();
        yvalues.toArray();
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 450, p);
        canvas.drawCircle((getWidth() / 2) + x, (getHeight() / 2) + y, 10, drawblack);
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
        postInvalidate();

        /**
        for(int i=0; i<xvalues.size(); i++){
            p.setColor(Color.BLACK);
            p.setStyle(Paint.Style.FILL);
            canvas.drawCircle((getWidth() / 2) + xvalues.get(i), (getHeight() / 2) + yvalues.get(i), 10, p);
            invalidate();
            postInvalidate();
            if(xvalues.get(i)==null){
                break;
            }

        }
         */

    }
}
