package com.example.Bestshift.Kommfort;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.Bestshift.MyActivity;
import com.example.Bestshift.R;
import android.view.View.OnClickListener;
import com.example.Bestshift.StartframeActivity;

/**
 * Created by Fitim on 23.11.2015.
 */
public class Kommfort extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.kommfort, container, false);
        RelativeLayout zukamm=(RelativeLayout) rootView.findViewById(R.id.zukamsh);
        RelativeLayout zubesh=(RelativeLayout) rootView.findViewById(R.id.zubesh);

        //Layout button=(Layout) rootView.findViewById(R.id.);
        zukamm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KammsherKreis.class);
                Kommfort.this.startActivity(intent);
            }
        });
        zubesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Beschleunigung.class);
                getActivity().startActivityForResult(myIntent,100);
            }
        });


        return rootView;
    }



}
