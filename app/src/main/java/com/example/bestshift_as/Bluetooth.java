package com.example.bestshift_as;

import android.app.Activity;
import android.bluetooth.*;
import android.content.*;
import android.content.Context;
import android.os.ParcelUuid;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Hüseyin on 09.03.2016.
 */

public class Bluetooth extends Activity {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final static int REQUEST_ENABLE_BT = 1;
    Intent enableBtIntent;
    private BluetoothSocket mmSocket = null;
    private BluetoothDevice mmDevice = null;
    private ArrayAdapter<String> mArrayAdapter;
    // 0000110e-0000-1000-8000-00805f9b34fb
    // 00000000-0000-1000-8000-00805f9b34fb
//    private static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    public Bluetooth() {
/*
        // TODO Move to onCreate?
        if(mBluetoothAdapter == null) {
            System.out.print("Sie müssen Bluetooth einschalten!");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }

    public Bluetooth(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
*/
        Set<BluetoothDevice> paired = mBluetoothAdapter.getBondedDevices();

        if(paired.size() >0 ) {
            for(BluetoothDevice device: paired) {
                Log.d("TEST", device.getName() + " " + device.getAddress());
                if (device.getName().equals("BSRP")) {
                    mmDevice = device;
                }
//                mArrayAdapter.add(device.getName()+ "\n" + device.getAddress());
            }
        }

            // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            ParcelUuid[] uuids = mmDevice.getUuids();
            for(ParcelUuid uuid : uuids) {
                Log.d("TEST", uuid.toString());
            }
            Log.d("TEST", "Create RfcommSocket on " + mmDevice.getName() + "...");
            if (mmDevice != null) {
                mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(myUUID);
            }
            Log.d("TEST", "Bluetooth Socket created for remote device " + mmSocket.getRemoteDevice().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            Log.d("TEST", "Connecting to RfcommSocket on " + mmDevice.getName() + "...");
            mmSocket.connect();
            Log.d("TEST", "Verbunden");
        } catch (IOException connectException) {
            Log.e("TEST", "There was an error while establishing connection. -> " + connectException.getMessage());
            /************** Fallback Socket based on ********/
        /* http://stackoverflow.com/questions/18657427/ioexception-read-failed-socket-might-closed-bluetooth-on-android-4-3/18786701#18786701*/
            Class<?> clazz = mmSocket.getRemoteDevice().getClass();
            Class<?>[] paramTypes = new Class<?>[] {Integer.TYPE};
            try {
                Method m = clazz.getMethod("createInsecureRfcommSocket", paramTypes);
                Object[] params = new Object[] {Integer.valueOf(1)};
                mmSocket = (BluetoothSocket) m.invoke(mmSocket.getRemoteDevice(), params);
                mmSocket.connect();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        try {
            mmSocket.close();
            System.out.print("Closed #2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BluetoothSocket createSocket(BluetoothDevice device) throws IOException {
        return device.createRfcommSocketToServiceRecord(myUUID);
    }

    public void run() {
        // Cancel discovery because it will slow down the connection
        mBluetoothAdapter.cancelDiscovery();
        Intent intent = getIntent();
        /**
        String address = intent.getStringExtra();
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(intent);
        **/
        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
            System.out.print("Verbunden");
        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }

    }

    /** Will cancel an in-progress connection, and close the socket */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }

    public  void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

}

