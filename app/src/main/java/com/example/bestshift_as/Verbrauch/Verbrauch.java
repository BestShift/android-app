package com.example.bestshift_as.Verbrauch;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bestshift_as.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * Created by Hüseyin on 23.11.2015.
 */
public class Verbrauch extends Fragment {
    View rootView;
    private RelativeLayout linechartlayout;
    private LineChart mchart;
    private LineData data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.verbrauch, container, false);
        RelativeLayout relativ =(RelativeLayout) rootView.findViewById(R.id.oberflaecheverbrauch);
        mchart = (LineChart) relativ.findViewById(R.id.chartverbrauch);
        //creating linechart
        //mchart = new LineChart(this);
        //add to layout

        //customizing the linechart

        mchart.setDescription("Verbrauchsanalyse");
        mchart.setNoDataTextDescription("No data found");

        //enable value highlighting
        //mchart.setHighlightPerTapEnabled(true);

        //enable touch features
        mchart.setTouchEnabled(true);

        // enable dragging
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(true);
        mchart.setDrawGridBackground(false);

        //alternative bgcolor
        mchart.setBackgroundColor(Color.LTGRAY);

        //LineData workings
        LineData ld = new LineData();
        ld.setValueTextColor(Color.WHITE);

        //adding data to linechart

        mchart.setData(ld);

        //getting legend object

        Legend l = mchart.getLegend();


        //customizing legend
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.BLACK);

        //xaxis
        XAxis x1 = mchart.getXAxis();
        x1.setTextColor(Color.BLACK);
        x1.setDrawGridLines(false);
        x1.setAvoidFirstLastClipping(true);

        //yaxis

        YAxis y1 = mchart.getAxisLeft();
        y1.setTextColor(Color.BLACK);
        y1.setDrawGridLines(true);
        y1.setAxisMaxValue(170f);

        YAxis y2 = mchart.getAxisRight();
        y2.setEnabled(false);

        //limitline
        LimitLine ll = new LimitLine(119f, "Preferred Maximum CO2 Emission");
        ll.setLineColor(Color.RED);
        ll.setLineWidth(3f);
        ll.setTextColor(Color.BLACK);
        ll.setTextSize(10f);

        y1.addLimitLine(ll);
        return rootView;
    }
    private void addEntry() {
        data=mchart.getData();
        if(data != null){
            LineDataSet set= data.getDataSetByIndex(0);
            if(set == null){
                set=createSet();
                data.addDataSet(set);
            }
            //add a new random value
            data.addXValue("");
            int range = (130 - 60) + 1;
            data.addEntry(new Entry((float)(Math.random()*range)+60f,set.getEntryCount()), 0);

            mchart.notifyDataSetChanged();

            mchart.setVisibleXRange(6,0);

            mchart.moveViewToX(data.getXValCount() -7);
        }
    }
    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "gCO2/sek");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);
        return set;
    }
    @Override
    public   void onResume() {
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

}

