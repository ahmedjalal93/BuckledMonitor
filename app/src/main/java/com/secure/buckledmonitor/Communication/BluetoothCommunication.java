package com.secure.buckledmonitor.Communication;

/**
 * Created by ajalal on 5/18/18.
 */

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import static com.secure.buckledmonitor.BluetoothPair.isConnected;
import static com.secure.buckledmonitor.MainActivity.TAG;


/**
 * Created by User on 6/5/2015.
 */
public class BluetoothCommunication extends Thread {

    BluetoothSocket socket = null;
    public static HashMap<Integer, Integer> seatBeltsMonitor = new HashMap<Integer, Integer>();

    public BluetoothCommunication(BluetoothSocket socket) {
        this.socket = socket;
    }

    public void sendData(String data) throws IOException {


        Log.i(TAG, "trying data send: " + data);

        byte[] buffer = new byte[1024];
        byte[] send = data.getBytes();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(send);
        Log.i(TAG, "data send: " + send);
    }

    public void run() {
        if(isConnected == 0){
            this.interrupt();
        }
        byte[] buffer = new byte[256];
        int bytes;
        InputStream inputStream = null;
        while (isConnected == 1) {
            try {
                inputStream = socket.getInputStream();
                bytes = inputStream.read(buffer);
                String readMessage = new String(buffer, 0, bytes);
                String[] seat = readMessage.split(":");
                seatBeltsMonitor.put(Integer.valueOf(seat[0]), Integer.valueOf(seat[1]));
            } catch (IOException e) {
                break;
            }
        }

    }
}