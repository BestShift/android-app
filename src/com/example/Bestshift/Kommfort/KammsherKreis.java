package com.example.Bestshift.Kommfort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RelativeLayout;
import com.example.Bestshift.FragementPageAdapter;
import android.view.View;
import android.widget.Button;
import com.example.Bestshift.MyActivity;
import com.example.Bestshift.R;
import com.example.Bestshift.Kommfort.Kommfort;
/**
 * Created by Fitim on 30.11.2015.
 */
public class KammsherKreis extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
