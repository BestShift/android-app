package com.example.bestshift_as.Stop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bestshift_as.EndeFahrt.Endanzeige;
import com.example.bestshift_as.Kommfort.Berechnenkomfort;
import com.example.bestshift_as.MyActivity;
import com.example.bestshift_as.R;

/**
 * Created by Fitim on 02.12.2015.
 */
public class Stop extends Activity {
    public TextView textende;
    Berechnenkomfort b;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Titel bar ausblenden
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.stop);
        RelativeLayout weiter = (RelativeLayout) findViewById(R.id.weiterausfuehren);
        RelativeLayout stop = (RelativeLayout) findViewById(R.id.stoppen);
        textende = (TextView)findViewById(R.id.kommfortendetext);
        b=new Berechnenkomfort();
        b.setSchalter(true);
        weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Endanzeige.class);
                startActivity(intent);
                b=new Berechnenkomfort();
                b.setSchalter(true);

            }
        });
    }
}
