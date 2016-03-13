package com.example.bestshift_as.Kommfort;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.bestshift_as.R;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * Created by Faiku Fitim on 23.11.2015.
 */
public class Kommfort extends Fragment implements OnChartValueSelectedListener {
    private RadarChart mChart;
    private Typeface tf;
    private LineChart mlayout;
    private RelativeLayout rlkamm;
    private LineChart mLineChart;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.kommfort, container, false);
        RelativeLayout relativ =(RelativeLayout) rootView.findViewById(R.id.bak);


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


        Button zukamm=(Button) rootView.findViewById(R.id.butonzumkreis);
        TextView zubesh=(TextView) rootView.findViewById(R.id.zubeschleunigungskraefte);

        //Layout button=(Layout) rootView.findViewById(R.id.);

        zukamm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KammsherKreis.class);
                Kommfort.this.startActivity(intent);
            }
        });

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


    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {

    }

    @Override
    public void onNothingSelected() {

    }
}