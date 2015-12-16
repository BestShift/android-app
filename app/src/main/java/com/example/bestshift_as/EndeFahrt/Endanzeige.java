package com.example.bestshift_as.EndeFahrt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bestshift_as.Kommfort.Berechnenkomfort;
import com.example.bestshift_as.R;

/**
 * Created by Fitim on 02.12.2015.
 */
public class Endanzeige extends Activity {
    public TextView text;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Titel bar ausblenden
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.endederfahrt);

        text =(TextView)findViewById(R.id.kommfortendetext);
        Berechnenkomfort b= new Berechnenkomfort();
        boolean var =b.getSchalter();
        if (var == true){
            text.setText("Hallo");
        }

    }

}
