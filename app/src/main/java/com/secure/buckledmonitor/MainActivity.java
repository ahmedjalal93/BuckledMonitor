package com.secure.buckledmonitor;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String TAG = "Buckled Control";

    public static BluetoothAdapter mBluetoothAdapter;
    private TextView mStatus;
    private Button startButton;

    private int ENABLE_BLUETOOTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStatus = (TextView) findViewById(R.id.bluetooth_status);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setupBluetooth()) {
                    Intent serverIntent = new Intent(getBaseContext(), BluetoothPair.class);
                    startActivityForResult(serverIntent, 1);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mStatus.setText("Start");


    }

    @Override
    public void onStop() {
        super.onStop();
        mStatus.setText("");
        startButton.setText("Start");
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public boolean setupBluetooth() {

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!(permissionCheck == PackageManager.PERMISSION_GRANTED)) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
            } else {
                // do request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        8);
            }
        }

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            mStatus.setText("Bluetooth is not available");
        } else {
            Log.i(TAG, "Bluetooth adapter is available, Continue...");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            mStatus.setText("Bluetooth is not enabled");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH);
        } else {
            Log.i(TAG, "Bluetooth is enabled, Continue...");
            return true;
        }
        return false;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ENABLE_BLUETOOTH) {
            if (resultCode == Activity.RESULT_OK) {
                startButton.setText("Connect");
                mStatus.setText("");
            }
        }
    }
}