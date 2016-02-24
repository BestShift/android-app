package com.example.bestshift_as.Shift;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bestshift_as.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Random;
import io.sule.gaugelibrary.GaugeView;

/**
 * A class which defines the Activity Shift as well as the algorithm for the gear change signal
 * @author Raphael Simsek
 * @version 1.0
 * @date 10/02/2016
 */
public class Shift extends Fragment {
    private GaugeView mGaugeView;
    private final int noChange=0;
    private final int changeUp=1;
    private final int changeDown=2;
    private int maxRpm=6000;
    /**
     * TODO:mit mehrdimensionalen Arrays maximal und 90% Werte speichern
     * bestenfalls nur eine Errechnung der Maximalwerte mit einem ungefähren Multiplikator,
     * wonach dann die shiftValues mit *0.9 berechnet werden.
    private int[][] shiftValues =
    private int[][] maxValues=
     */
    private final Random random = new Random(); // used to create the number shown in the Gauge
    // mTimer creates the Clicks on which the Gauge changes it's value
    private final CountDownTimer mTimer = new CountDownTimer(30000,1000) {
        /**
         * Sets the Value, once the Tick has happened
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            mGaugeView.setTargetValue(random.nextInt());
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

    public int calculateGear(int calculatedLoad, int rpm) {
        //Calculation of Change Up
        if(rpm >= getMaxRpm()*0.9){
            return changeUp;
        }else if(calculatedLoad >= 90){
            return changeUp;
        }

        //Calculation of Change Down
        if(rpm >=600 && rpm<=650 && calculatedLoad<=10) {
            return changeDown;
        }
        if(rpm >650 && rpm <=700 && calculatedLoad<=25 && calculatedLoad>10){
            return changeDown;
        }
        if (rpm >700 && rpm<=750 && calculatedLoad<=40 && calculatedLoad>25){
            return changeDown;
        }
        if(rpm >750 && rpm<=800 && calculatedLoad<=55 && calculatedLoad>40){
            return changeDown;
        }

        //Return if none of the upper cases turn out true
        return noChange;
    }

    public int getMaxRpm() {
        return maxRpm;
    }

    public void setMaxRpm(int maxRpm) {
        this.maxRpm = maxRpm;
    }
}
