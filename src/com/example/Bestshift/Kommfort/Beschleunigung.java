package com.example.Bestshift.Kommfort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.Bestshift.MyActivity;
import com.example.Bestshift.R;

/**
 * Created by Fitim on 30.11.2015.
 */
public class Beschleunigung extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Titel bar ausblenden
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.beschleunigungswerte);
        RelativeLayout vonbeshzumain = (RelativeLayout) findViewById(R.id.vonbeshzumain);
        vonbeshzumain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);
            }
        });
    }
}
