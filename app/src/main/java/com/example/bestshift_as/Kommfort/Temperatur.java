package com.example.bestshift_as.Kommfort;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bestshift_as.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by fitim on 25.01.2016.
 */
public class Temperatur extends Activity {
    private ImageView temp_icon;
    private TextView currenttemp;
    private JSONObject tempdata;
    private int icon_id, color_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kommfort);


        currenttemp = (TextView)findViewById(R.id.main_txt_currenttemp);


        try {
            UpdateData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        try {
            UpdateData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void UpdateData() throws JSONException {
        tempdata = getJsonData();
        showTempIcon(Float.valueOf(tempdata.get("CURRENTTEMP").toString()));


        temp_icon.setBackgroundResource(icon_id);
        currenttemp.setText(tempdata.get("CURRENTTEMP").toString() + " \u2103");
        currenttemp.setTextColor(color_id);
    }

    private void showTempIcon(Float temp) {
        if (temp >= 28){
            icon_id = R.drawable.hot1;
            color_id = getResources().getColor(R.color.hot1);
        }
        if (temp < 28 && temp >= 21){
            icon_id = R.drawable.hot2;
            color_id = getResources().getColor(R.color.hot2);
        }
        if (temp < 21 && temp >= 16){
            icon_id = R.drawable.hot3;
            color_id = getResources().getColor(R.color.hot3);
        }
        if (temp < 16 && temp >= 10){
            icon_id = R.drawable.cold1;
            color_id = getResources().getColor(R.color.cold1);
        }
        if (temp < 10 && temp >= 3){
            icon_id = R.drawable.cold2;
            color_id = getResources().getColor(R.color.cold2);
        }
        if (temp < 3){
            icon_id = R.drawable.cold3;
            color_id = getResources().getColor(R.color.cold3);
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject getJsonData() throws JSONException {
        JSONObject tcpData = new JSONObject();
        tcpData.put("ID","TEMP");
        tcpData.put("COMMAND","CURRENTTEMP");
        JSONObject response = new JSONObject();
        try {
            response = new TCPConnect().execute(tcpData).get();
        } catch (InterruptedException e) {
            response.put("ERR",e.toString());
        } catch (ExecutionException e) {
            response.put("ERR",e.toString());
        }
        return response;
    }

}