package com.example.bestshift_as.Kommfort;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.bestshift_as.MyActivity;
import com.example.bestshift_as.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * Created by fitim on 09.12.2015.
 */
public class Beschleunigungskraefte extends Activity {
    private LineChart mlayout;
    private LineChart mChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Titel bar ausblenden
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.beschl);
        mlayout = (LineChart) findViewById(R.id.chart1);
        // Create line chart
        mChart = new LineChart(this);
        //add to main Layout
        mlayout.addView(mChart);

        // costumize line chart
        mChart.setDescription("Beschleunigungskraefte");

        mChart.setNoDataText("Keine Daten bro :(");

        // enable Value highlighting
        mChart.setHighlightEnabled(true);
        // Enable touch feauters
        mChart.setTouchEnabled(true);

        // we want also enable and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // enable pinch zoom to avoid scaling x and y axis separat
        mChart.setPinchZoom(true);

        //alternative Background
        mChart.setBackgroundColor(Color.LTGRAY);

        // now we work on data
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add data to line chart
        mChart.setData(data);

        // get legend objecz
        Legend l = mChart.getLegend();

        // costumize legend
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis x1 = mChart.getXAxis();
        x1.setTextColor(Color.WHITE);
        x1.setDrawGridLines(false);
        x1.setAvoidFirstLastClipping(true);

        YAxis y1 = mChart.getAxisLeft();
        y1.setTextColor(Color.WHITE);
        y1.setAxisMaxValue(120f);
        y1.setDrawGridLines(true);

        YAxis y12 = mChart.getAxisRight();
        y12.setEnabled(false);



        RelativeLayout vonbeshzumain = (RelativeLayout) findViewById(R.id.vonbeshzumenu);
        vonbeshzumain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);
            }
        });


    }




    @Override
    protected void onResume() {
        super.onResume();
        // now we are going to simulate real time data addition
        new Thread(new Runnable() {
            @Override
            public void run() {
                // add 100 entries
                for(int i=0; i<100; i++){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();//mchart is modified of update in addEntry method
                        }
                    });
                    // pause between adds
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // we need to create method to add entry to the line Chart
    private void addEntry(){
        LineData data=mChart.getData();
        if(data != null){
            LineDataSet set = data.getDataSetByIndex(0);
            if(set == null){
                //creation if null
                set=createSet();
                data.addDataSet(set);
            }
            // add a new random value
            data.addXValue("");
            data.addEntry(new Entry((float)(Math.random() *75) +60f,set.getEntryCount()), 0);
            // enble the way chart know when its data has changed
            mChart.notifyDataSetChanged();

            //limit number of visible entries
            mChart.setVisibleXRange(6,00);

            //scroll to the last entry
            mChart.moveViewToX(data.getXValCount() - 7);


        }
    }
    // Methode to create set
    private LineDataSet createSet(){
        LineDataSet set=new LineDataSet(null, "SPL Db");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 177));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);
        return set;
    }


}