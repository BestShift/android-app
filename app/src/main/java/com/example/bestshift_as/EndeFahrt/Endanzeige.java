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
    public static TextView text;
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
        Button stopb= (Button) findViewById(R.id.stop);
        Berechnenkomfort b= new Berechnenkomfort();
        boolean a=b.getSchalter();
        if (a== true){
            text.setText("Hallo");
        }

    }

}
