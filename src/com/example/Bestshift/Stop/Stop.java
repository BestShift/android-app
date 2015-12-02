package com.example.Bestshift.Stop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import com.example.Bestshift.EndeFahrt.Endanzeige;
import com.example.Bestshift.MyActivity;
import com.example.Bestshift.R;

/**
 * Created by Fitim on 02.12.2015.
 */
public class Stop extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop);
        RelativeLayout weiter = (RelativeLayout) findViewById(R.id.weiterausfuehren);
        RelativeLayout stop = (RelativeLayout) findViewById(R.id.stoppen);

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
            }
        });
    }
}
