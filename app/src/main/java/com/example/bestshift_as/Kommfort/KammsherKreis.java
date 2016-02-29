package com.example.bestshift_as.Kommfort;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;
import android.widget.RelativeLayout;
import com.example.bestshift_as.FragementPageAdapter;
import android.view.View;
import android.widget.Button;
import com.example.bestshift_as.MyActivity;
import com.example.bestshift_as.R;
import com.example.bestshift_as.Kommfort.Kommfort;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Fitim on 30.11.2015.
 */
public class KammsherKreis extends Activity implements OnChartValueSelectedListener {
    private RadarChart mChart;
    private Typeface tf;
    int cnt = 50;
    private String[] mParties = new String[cnt];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kammsherkreis);
        mChart = (RadarChart) findViewById(R.id.chart2);


        mChart.setDescription("Hallo");

        mChart.setWebLineWidth(1.5f);
        mChart.setWebLineWidthInner(0.55f);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it

        // set the marker to the chart
        RadarData data = new RadarData();
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setTextSize(6f);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setTypeface(tf);
        yAxis.setLabelCount(5, false);
        yAxis.setAxisMaxValue(2f);
        yAxis.setTextSize(6f);
        yAxis.setStartAtZero(false);
        for(int i=0; i<cnt; i++){
            mParties[i]="Eingabe" + i;
        }

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setTypeface(tf);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(7f);
        RelativeLayout rl= (RelativeLayout) findViewById(R.id.vonkammzumenue);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.radar, menu);
        return true;
    }

    private void addEntry() {
        float mult = 2;

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        RadarData data = mChart.getData();
        if (data != null) {
            RadarDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = createSet();
                data.addDataSet(set);

                    for (int j = 0; j < cnt; j++) {
                        data.addEntry(new Entry((float) (Math.random() * 2) + 1f, set.getEntryCount()), 0);
                    }
                    //data.notifyDataChanged();
                    mChart.invalidate();
                    mChart.setSkipWebLineCount(5);










            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // add 100 entries
                for(int i=0; i<100; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private RadarDataSet createSet() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < cnt; i++) {
            yVals1.add(new Entry((float) (0.8), i));
        }
        RadarDataSet set1 = new RadarDataSet(yVals1, "Wohlfuehl bereich");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(null, "Daten");
        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        set2.setDrawFilled(false);
        set2.setLineWidth(2f);

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < cnt; i++) {
            xVals.add(mParties[i % mParties.length]);
        }

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