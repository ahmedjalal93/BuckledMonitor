package com.secure.buckledmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secure.buckledmonitor.Communication.BluetoothCommunication;
import com.secure.buckledmonitor.Communication.BluetoothConnect;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.secure.buckledmonitor.BluetoothPair.isConnected;
import static com.secure.buckledmonitor.Communication.BluetoothCommunication.seatBeltsMonitor;
import static com.secure.buckledmonitor.Communication.BluetoothConnect.mBluetoothSocket;
import static com.secure.buckledmonitor.MainActivity.TAG;

public class Monitor extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor);

        Thread t1 = new Thread(new BluetoothCommunication(mBluetoothSocket));
        t1.start();



        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(isConnected == 0){
                            timer.cancel();
                            timer.purge();
                            finish();
                            Intent serverIntent = new Intent(getBaseContext(), MainActivity.class);
                            startActivityForResult(serverIntent, 1);
                        }
                        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.monitor);
                        for (Map.Entry me : seatBeltsMonitor.entrySet()) {
                            Log.i(TAG, "Key: "+me.getKey() + " & Value: " + me.getValue());


                            switch ((Integer) me.getKey()){
                                case KeyEvent.KEYCODE_V:
                                    TextView sb1 = (TextView)findViewById(R.id.seatbelt1);
                                    sb1.setText("Seatbelt 1 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_M:
                                    TextView sb2 = (TextView)findViewById(R.id.seatbelt2);
                                    sb2.setText("Seatbelt 2 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_C:
                                    TextView sb3 = (TextView)findViewById(R.id.seatbelt3);
                                    sb3.setText("Seatbelt 3 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_E:
                                    TextView sb4 = (TextView)findViewById(R.id.seatbelt4);
                                    sb4.setText("Seatbelt 4 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_R:
                                    TextView sb5 = (TextView)findViewById(R.id.seatbelt5);
                                    sb5.setText("Seatbelt 5 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_U:
                                    TextView sb6 = (TextView)findViewById(R.id.seatbelt6);
                                    sb6.setText("Seatbelt 6 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_I:
                                    TextView sb7 = (TextView)findViewById(R.id.seatbelt7);
                                    sb7.setText("Seatbelt 7 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_O:
                                    TextView sb8 = (TextView)findViewById(R.id.seatbelt8);
                                    sb8.setText("Seatbelt 8 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_P:
                                    TextView sb9 = (TextView)findViewById(R.id.seatbelt9);
                                    sb9.setText("Seatbelt 9 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                                case KeyEvent.KEYCODE_Q:
                                    TextView sb10 = (TextView)findViewById(R.id.seatbelt10);
                                    sb10.setText("Seatbelt 10 is " + ((Integer) me.getValue()==1 ? "Buckled" : "Unbuckled"));
                                    break;
                            }


                            seatBeltsMonitor.put((Integer) me.getKey(),0);
                        }
                    }
                });
            }
        }, 0, 3000);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
