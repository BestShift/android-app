package com.example.bestshift_as.Kommfort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.StrictMath.round;

/**
 * Eine Klasse welches einen Kreis zeichnet in dem für alle Richtungen die Beschleunigungskräfte angezeigt werden.
 * Diese Punkte werden im 5 grad Bereich eingezeichnet und werden von neueren Extremwerten überschreben. 
 * Created by fitim on 10.03.2016.
 */
public class ChartView extends View {
	
    float phi;
    float y, y1,y2, y22;
    float x, x1, x2, x22;
    float value2, value3;

    float radius=400;
    float radiusgeneriert;
    HashMap hm=new HashMap();
    int key;
    float value;


    List<Float> xvalues = new ArrayList<Float>();
    List<Float> yvalues = new ArrayList<Float>();
    List<Float> mockdaten = new ArrayList<Float>();
	/*
	*Die Methode runden() nimmt alle phi Einträge auf und rundet diese auf 5 er Schritten und gibt sie Zurück.
	*
	*/
    public int runden(float phi){
        float anzahl= round(phi/5);
        System.out.println("Gerundet:" + anzahl);
        int returnzahl=0;
        returnzahl= (int) (anzahl*5);
        return returnzahl;
    }


    // CONSTRUCTOR
    public ChartView(Context context) {
        super(context);
        setFocusable(true);


    }

    @Override
	/**
	* Die Methode onDraw Zeichnet die Ganzen Kreise mittels Canvas.
	*/
    protected void onDraw(Canvas canvas) {
        int w = getWidth() / 2;
        int h = getHeight() / 2;



        Paint drawblack = new Paint();
        drawblack.setColor(Color.BLACK);
        drawblack.setStyle(Paint.Style.STROKE);
        drawblack.setStyle(Paint.Style.FILL);
        Paint p = new Paint();
        // smooths
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(4.5f);
        p.setStyle(Paint.Style.FILL);
        // opacity
        //p.setAlpha(0x80); //
        phi = (int) (Math.random() * (358  + 1) +0);
        System.out.println("in:" +phi);
        int a=runden(phi);
        System.out.println("out:" + a);

        radiusgeneriert=(int) (Math.random() * (radius  + 1) +0);


        Float isvalue= (Float) hm.get(a);

        x1= (float) (radiusgeneriert*Math.cos(a));
        y1= (float) (radiusgeneriert*Math.sin(a));
        //Der aktuelle Punkt wird angezeigt
        canvas.drawCircle((getWidth() / 2) + x1, (getHeight() / 2) + y1, 10, drawblack);
        if(isvalue != null) {
            float key2 = (float) hm.get(a);
            if (key2 <= radiusgeneriert){
                hm.put(a, radiusgeneriert);
            }
            else{

            }
        }
        else{
            hm.put(a, radiusgeneriert);
        }
        Set set=hm.entrySet();

        // Iterator noch
        Iterator i=set.iterator();

        /**
         * Die Hashmap wird iteriert und
         * dabei werden alle Werte die in der Hashmap gespeichert sind als Punkte gezeichnet
         *
         */
        while(i.hasNext()){
            Map.Entry me= (Map.Entry) i.next();
            key = (int) me.getKey();
            value = (float) me.getValue();
            x= (float) (value*Math.cos(key));
            y= (float) (value*Math.sin(key));


            canvas.drawCircle((getWidth() / 2) + x, (getHeight() / 2) + y, 3, drawblack);

            invalidate();
            postInvalidate();

        }

        /**
        int j=0;
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, p);
        while(j<=72){
            Float value2=(Float) hm.get(j*5);
            while(value2==null && j<=72){
                j++;
                value2=(Float) hm.get(j*5);
            }
            if (value2 != null) {
                x2= (float) (value2*Math.cos(j * 5));
                y2= (float) (value2*Math.sin(j*5));
                j++;
                Float value3=(Float) hm.get(j*5);
                while(value3 == null && j<=72){
                    j++;
                    value3=(Float) hm.get(j*5);
                }
                if (value3 != null) {
                    x22 = (float) (value3 * Math.cos(j * 5));
                    y22 = (float) (value3 * Math.sin(j * 5));
                    canvas.drawLine(getWidth() / 2 + x2, getHeight()/2 +y2, getWidth() / 2 + x22, getHeight()/2 +y22, drawblack);
                    j++;
                }
            }


        }
         */


        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        //Hier wird der größere Kreis gezeichnet
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, p);
        invalidate();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
