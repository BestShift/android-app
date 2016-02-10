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
    Berechnenkomfort b;
    boolean var;
    int count;
    /**
     * Called when the activity is first created.
     */

    public Endanzeige(){
        b= new Berechnenkomfort();
        var=b.getSchalter();
        count=b.getkommfortcount();
    }
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

            if(count>10 && count<=16){
                text.setText("Du bist sehr gut gefahren. Du bist ein sehr vorbildlicher Fahrer");
            }
            if(count>=17 && count<=21){
                text.setText("Du hast hin und wieder zu viel Beschleunigt und oder zu schnell die Kurve genommen.");

            }
            if(count>=22 && count<28){
                text.setText("Du könntest dich wirklich verbessern würdest du auf die App hören, deine Fahrt war den Beifahrern nicht seh angenehm");
            }
        }

    }

}
