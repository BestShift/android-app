package com.example.Bestshift.EndeFahrt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.example.Bestshift.R;

/**
 * Created by Fitim on 02.12.2015.
 */
public class Endanzeige extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Titel bar ausblenden
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.endederfahrt);

    }
}
