package com.example.bestshift_as;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Created by Fitim on 02.12.2015.
 */
public class StartframeActivity extends Activity {
        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.startlayout);
            //all contents
            Button button1 = (Button) findViewById(R.id.button);
            ImageView imgViewBS = (ImageView) findViewById(R.id.imageView11);
            ImageView imgIcon = (ImageView) findViewById(R.id.imageView2);
            //Rotating icon :)
            Animation iconRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
            imgIcon.startAnimation(iconRotate);

            //BestShift fadein
            final Animation BsFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
            imgViewBS.startAnimation(BsFadeIn);

            final Animation StartPulse = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
            button1.startAnimation(StartPulse);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                    startActivity(intent);
                }
            });


//            Set<BluetoothDevice> paired = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
            BluetoothDevice mmDevice = null;
            /**
            if(paired.size() >0 ) {
                for(BluetoothDevice device: paired) {
                    Log.d("TEST", device.getName() + " " + device.getAddress());
                    if (device.getName().equals("BSRP")) {
                        mmDevice = device;
                    }
//                mArrayAdapter.add(device.getName()+ "\n" + device.getAddress());
                }
            }
            BluetoothConnector connector;
            try {
                connector = new BluetoothConnector(mmDevice, false, BluetoothAdapter.getDefaultAdapter(),null);
                BluetoothConnector.BluetoothSocketWrapper socket = connector.connect();
                InputStream stream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
             */

        }
    }


