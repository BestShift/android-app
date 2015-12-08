package com.example.bestshift_as.Kommfort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RelativeLayout;
import com.example.bestshift_as.FragementPageAdapter;
import android.view.View;
import android.widget.Button;
import com.example.bestshift_as.MyActivity;
import com.example.bestshift_as.R;
import com.example.bestshift_as.Kommfort.Kommfort;
/**
 * Created by Fitim on 30.11.2015.
 */
public class KammsherKreis extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Titel bar ausblenden
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.kammsherkreis);
        RelativeLayout vonkammzukomm = (RelativeLayout) findViewById(R.id.vonkammzukomm);
        vonkammzukomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);



            }
        });
    }
}
