package com.example.bestshift_as.Kommfort;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.bestshift_as.MyActivity;
import com.example.bestshift_as.R;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.bestshift_as.StartframeActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Faiku Fitim on 23.11.2015.
 */
public class Kommfort extends Fragment implements OnChartValueSelectedListener {
    private RadarChart mChart;
    private Typeface tf;
    private LineChart mlayout;
    private LineChart mLineChart;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.kommfort, container, false);
        RelativeLayout relativ =(RelativeLayout) rootView.findViewById(R.id.bak);
        mChart = (RadarChart) relativ.findViewById(R.id.radarchartkommfort);

        mLineChart = (LineChart) relativ.findViewById(R.id.linechartkommfort);
        mLineChart.setOnChartValueSelectedListener(this);

        // no description text
        mLineChart.setDescription("Beschleunigungskraefte");
        mLineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable highlighting
        mLineChart.setHighlightEnabled(true);

        // enable touch gestures
        mLineChart.setTouchEnabled(true);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);

        // set an alternative background color
        mLineChart.setBackgroundColor(Color.WHITE);

        LineData data = new LineData();
        data.setValueTextColor(Color.BLACK);

        // add empty data
        mLineChart.setData(data);



        // get the legend (only possible after setting data)
        Legend l = mLineChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        l.setTextColor(Color.BLACK);

        XAxis xl = mLineChart.getXAxis();

        xl.setTextColor(Color.BLACK);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);


        YAxis leftAxis = mLineChart.getAxisLeft();

        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMaxValue(2f);
        leftAxis.setAxisMinValue(0f);
        leftAxis.setStartAtZero(false);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);

        mChart.setDescription("Kammscher-Kreis");

        mChart.setWebLineWidth(1.5f);
        mChart.setWebLineWidthInner(0.55f);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it

        // set the marker to the chart
        RadarData radardata = new RadarData();
        radardata.setValueTextColor(Color.BLACK);
        mChart.setData(radardata);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setTextSize(6f);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setTypeface(tf);
        yAxis.setLabelCount(5, false);
        yAxis.setAxisMaxValue(2f);
        yAxis.setTextSize(6f);
        yAxis.setStartAtZero(true);

        Legend lradar = mChart.getLegend();
        lradar.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        lradar.setTypeface(tf);
        lradar.setXEntrySpace(7f);
        lradar.setYEntrySpace(7f);

       // TextView zukamm=(TextView) rootView.findViewById(R.id.zukammsherkreis);
        TextView zubesh=(TextView) rootView.findViewById(R.id.zubeschleunigungskraefte);

        //Layout button=(Layout) rootView.findViewById(R.id.);

/*        zukamm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KammsherKreis.class);
                Kommfort.this.startActivity(intent);
            }
        });
        */
        zubesh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Beschleunigungskraefte.class);
                getActivity().startActivityForResult(myIntent,100);
            }
        });



        return rootView;
    }
    private String[] mParties = new String[]{
            "Bremsen", "Linkskurve", "Gas", "Rechtskurve"
    };

    private void addEntryRadar() {
        float mult = 2;
        int cnt = 4;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        RadarData data = mChart.getData();
        if (data != null) {
            RadarDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = createSetRadar();
                data.addDataSet(set);

                for (int i = 0; i < cnt; i++) {
                    yVals2.add(new Entry((float)(Math.random()*2)+0.1f, set.getEntryCount(), 0));
                }
                mChart.notifyDataSetChanged();
                mChart.invalidate();




            }
        }
    }
    /**
     public void setData() {

     float mult = 2;
     int cnt = 4;
     String[] yVals = new String[]{
     "yVals1", "yVals2"
     };
     ArrayList<Entry> yVals1 = new ArrayList<Entry>();
     ArrayList<Entry> yVals2 = new ArrayList<Entry>();
     ArrayList<Entry> yVals3 = new ArrayList<Entry>();

     // IMPORTANT: In a PieChart, no values (Entry) should have the same
     // xIndex (even if from different DataSets), since no values can be
     // drawn above each other.

     for (int i = 0; i < cnt; i++) {
     yVals1.add(new Entry((float) (0.8), i));
     }

     for (int i = 0; i < cnt; i++) {
     yVals2.add(new Entry((float) (2.0), i));
     }

     ArrayList<String> xVals = new ArrayList<String>();

     for (int i = 0; i < cnt; i++)
     xVals.add(mParties[i % mParties.length]);

     RadarDataSet set1 = new RadarDataSet(yVals1, "Wohlfuehl bereich");
     set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
     set1.setDrawFilled(true);
     set1.setLineWidth(2f);

     RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
     set2.setColor(ColorTemplate.COLOR_NONE);
     set2.setDrawFilled(true);
     set2.setLineWidth(2f);

     ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
     sets.add(set1);
     sets.add(set2);

     RadarData data = new RadarData(xVals, sets);
     data.setValueTypeface(tf);
     data.setValueTextSize(10f);
     data.setDrawValues(false);

     mChart.setData(data);

     mChart.invalidate();
     }
     **/
    private void addEntryKamm() {
        float mult = 2;
        int cnt = 4;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        RadarData data = mChart.getData();
        if (data != null) {
            RadarDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = createSetRadar();
                data.addDataSet(set);

                for (int i = 0; i < cnt; i++) {
                    yVals2.add(new Entry((float)(Math.random()*2)+0.1f, set.getEntryCount(), 0));
                }
                mChart.notifyDataSetChanged();
                mChart.invalidate();




            }
        }
    }

    private void addEntry() {
        LineData data=mLineChart.getData();
        if(data != null){
            LineDataSet set= data.getDataSetByIndex(0);
            if(set == null){
                set=createSet();
                data.addDataSet(set);
            }
            //add a new random value
            data.addXValue("");
            data.addEntry(new Entry((float)(Math.random()*2)+0.1f,set.getEntryCount()), 0);

            mLineChart.notifyDataSetChanged();

            mLineChart.setVisibleXRange(6,0);

            mLineChart.moveViewToX(data.getXValCount() -7);
        }
    }
    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Realtime Beschleunigungskraefte");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.BLACK);
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(10f);
        return set;
    }
    @Override
    public void onResume(){
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // add 100 entries
                for(int i=0; i<100; i++) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                            addEntryKamm();
                        }
                    });
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private RadarDataSet createSetRadar() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < 4; i++) {
            yVals1.add(new Entry((float) (0.8), i));
        }
        RadarDataSet set1 = new RadarDataSet(yVals1, "Wohlfuehl bereich");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(null, "Daten");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < 4; i++)
            xVals.add(mParties[i % mParties.length]);

        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
        sets.add(set1);
        sets.add(set2);



        RadarData data = new RadarData(xVals, sets);
        mChart.notifyDataSetChanged();
        data.setValueTypeface(tf);
        data.setValueTextSize(10f);
        data.setDrawValues(false);
        mChart.setData(data);
        mChart.invalidate();

        return set2;
    }

    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {

    }

    @Override
    public void onNothingSelected() {

    }
}