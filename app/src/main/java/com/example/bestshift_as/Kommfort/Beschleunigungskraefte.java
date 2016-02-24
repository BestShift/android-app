package com.example.bestshift_as.Kommfort;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestshift_as.MyActivity;
import com.example.bestshift_as.R;
import com.github.mikephil.charting.charts.LineChart;
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
 * Created by fitim on 09.12.2015.
 */
public class Beschleunigungskraefte extends Activity implements OnChartValueSelectedListener {
    private LineChart mlayout;
    private LineChart mLineChart;
    private MediaPlayer mpAudio;
    private MediaPlayer mpAudio2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beschl);
        mLineChart = (LineChart) findViewById(R.id.chart1);
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
        LineData data2 = new LineData();
        data2.setValueTextColor(Color.RED);

        // add empty data
        mLineChart.setData(data);
        mLineChart.setData(data2);




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
        TextView rl= (TextView) findViewById(R.id.frombeschtokommtext);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addEntry2() {

        LineData data2=mLineChart.getData();
        if(data2!=null){
            LineDataSet set2= data2.getDataSetByIndex(0);
            if(set2 == null){
                set2=createSet2();
                data2.addDataSet(set2);

            }
            //add a new random value
            data2.addXValue("" + set2.getEntryCount());
            float a=(float) Math.random() * 2 + 0.1f;
            data2.addEntry(new Entry(a, set2.getEntryCount()), 0);

            mLineChart.notifyDataSetChanged();

            mLineChart.setVisibleXRange(6,0);

            mLineChart.moveViewToX(data2.getXValCount() -7);
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
            data.addXValue("" + set.getEntryCount());
            float a=(float) Math.random() * 2 + 0.1f;
            if(a>1.7){
                feedback1();
                mpAudio.start();
            }
            data.addEntry(new Entry(a, set.getEntryCount()), 0);

            mLineChart.notifyDataSetChanged();

            mLineChart.setVisibleXRange(6,0);

            mLineChart.moveViewToX(data.getXValCount() -7);
        }
    }
    private void feedback1(){
        Context context = getApplicationContext();
        CharSequence text = "Langsam!!";
        int duration=Toast.LENGTH_SHORT;

        Toast toast=Toast.makeText(context, text, duration);
        toast.show();
        mpAudio=MediaPlayer.create(this, R.raw.sound);


    }
    private void feedback2(){
        Context context = getApplicationContext();
        CharSequence text = "Langsamer!!";
        int duration=Toast.LENGTH_SHORT;

        Toast toast=Toast.makeText(context, text, duration);
        toast.show();
        mpAudio2=MediaPlayer.create(this, R.raw.sound);
    }


    private LineDataSet createSet2() {

        LineDataSet set2 = new LineDataSet(null, "Realtime Beschleunigungskraefte");

        set2.setDrawCubic(true);
        set2.setCubicIntensity(0.2f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(Color.BLUE);
        set2.setCircleColor(Color.RED);
        set2.setLineWidth(2f);
        set2.setCircleSize(4f);
        set2.setFillAlpha(65);
        set2.setFillColor(ColorTemplate.getHoloBlue());
        set2.setHighLightColor(Color.rgb(244, 117, 117));
        set2.setValueTextColor(Color.BLACK);
        set2.setValueTextSize(10f);
        return set2;
    }
    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Realtime Beschleunigungskraefte1");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_realtime_line, menu);
        return true;
    }
    @Override
    protected  void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // add 100 entries
                for(int i=0; i<40; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();

                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionAdd: {
                addEntry();
                break;
            }
            case R.id.actionClear: {
                mLineChart.clearValues();
                Toast.makeText(this, "Chart cleared!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.actionFeedMultiple: {
                onResume();
                break;
            }
        }
        return true;
    }

    @Override
    public void onValueSelected(Entry e, int i, Highlight highlight) {
        Log.i("Entry selected", e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

}