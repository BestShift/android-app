package com.example.bestshift_as.Shift;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bestshift_as.R;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;
import io.sule.gaugelibrary.GaugeView;

/**
 * A class which defines the Activity Shift as well as the algorithm for the gear change signal
 * @author Raphael Simsek
 * @version 1.0
 * @date 10/02/2016
 */
public class Shift extends Fragment {
    private int maxrpm=100;
    private int minrpm=2;
    private int rpm=0;
    private boolean countsDown=false;
    private int change=0;
    private RelativeLayout relativ;

    private GaugeView mGaugeView;
    private final int noChange=0;
    private final int changeUp=1;
    private final int changeDown=2;
    //private int[][] downShiftValues = { {600,650,700,750,800},{10,20,40,70,90} };
    /**
     * TODO:mit mehrdimensionalen Arrays maximal und 90% Werte speichern
     * bestenfalls nur eine Errechnung der Maximalwerte mit einem ungefähren Multiplikator,
     * wonach dann die shiftValues mit *0.9 berechnet werden.
     * 600 als untestes Granze einführen und 6000 als obere Grenze und User dazwischen definieren lassen
    private int[][] shiftValues =
    private int[][] maxValues=
     */
    private final Random random = new Random(); // used to create the number shown in the Gauge
    // mTimer creates the Clicks on which the Gauge changes it's value
    private final CountDownTimer mTimer = new CountDownTimer(3000000,10) {
        /**
         * Sets the Value, once the Tick has happened
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            if(rpm==maxrpm){
                countsDown=true;
            }
            if(countsDown){
                rpm--;
                if(rpm<=25){
                    change=1;
                }else {
                    change = 0;
                }
            }else {
                rpm++;
                if(rpm>=75){
                    change=2;
                }else {
                    change=0;
                }
            }
            if(rpm==minrpm){
                countsDown=false;
            }
            gaugeValueChange(rpm);
            gearChangeImage(change);
        }

        /**
         * Overrides onFinish method to not take an action on the finish
         */
        @Override
        public void onFinish() {
        }
    };

    /**
     * Creates the View of the gauge on creation of the Activity
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return returns layout of the GaugeView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTimer.start();
        View rootView = inflater.inflate(R.layout.shalt, container, false);
        mGaugeView = (GaugeView)rootView.findViewById(R.id.gauge_view);
        this.relativ = (RelativeLayout) rootView.findViewById(R.id.schalt);
        return rootView;
    }

    /**
     * Defines options for Home Button and handles action bar items
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so  long
        // as you specify a parent activity in   AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public int calculateGear(int calculatedLoad, int rpm) {
//        //Calculation of Change Up
//        if(rpm >= getMaxRpm()*0.9){
//            return changeUp;
//        }else if(calculatedLoad >= 90){
//            return changeUp;
//        }
//
//        //Calculation of Change Down
//        if(rpm >=downShiftValues[1][1] && rpm<=downShiftValues[1][2] && calculatedLoad<=downShiftValues[2][2]) {
//            return changeDown;G
//        }
//        if(rpm >downShiftValues[1][2] && rpm <=downShiftValues[1][3] && calculatedLoad<=downShiftValues[2][3] && calculatedLoad>downShiftValues[2][2]){
//            return changeDown;
//        }
//        if (rpm >downShiftValues[1][3] && rpm<=downShiftValues[1][4] && calculatedLoad<=downShiftValues[2][4] && calculatedLoad>downShiftValues[2][3]){
//            return changeDown;
//        }
//        if(rpm >downShiftValues[1][4] && rpm<=downShiftValues[1][5] && calculatedLoad<=downShiftValues[2][5] && calculatedLoad>downShiftValues[2][4]){
//            return changeDown;
//        }
//
//        //Return if none of the upper cases turn out true
//        return noChange;
//    }

    public void gaugeValueChange(int time){
        mGaugeView.setTargetValue(time);
    }

    public void gearChangeImage(int change){

        if(change==1){
            //Bild unten
            int id = getResources().getIdentifier("com.example.bestshift_as:drawable/ic_arrow_downward_black_48dp", null, null);
            ImageView i=(ImageView) relativ.findViewById(R.id.img);
            i.setImageResource(id);
        }
        if(change==2){
            //Bild oben
            int id = getResources().getIdentifier("com.example.bestshift_as:drawable/ic_arrow_upward_black_48dp", null, null);
            ImageView i=(ImageView) relativ.findViewById(R.id.img);
            i.setImageResource(id);
        }
        if(change==0){
            //Bild X
            int id = getResources().getIdentifier("com.example.bestshift_as:drawable/ic_close_black_48dp", null, null);
            ImageView i=(ImageView) relativ.findViewById(R.id.img);
            i.setImageResource(id);
        }
    }

}
