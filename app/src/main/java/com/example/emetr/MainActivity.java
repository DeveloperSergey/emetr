package com.example.emetr;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  BLESearch.BLESearchCallback, BLEConnector.BLEConnectorCallbacks{

    BLESearch bleSearch;
    BLEConnector bleConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ScreenView screenView = new ScreenView(getApplicationContext());
        setContentView(screenView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        Log.d("emetr_activity", "App started.");

        // Bluetooth
        bleSearch = new BLESearch(this, this);
        bleSearch.startScan();
    }

    @Override
    public void bleDeviceFound(BluetoothDevice device) {
        if( (bleConnector == null) && (device.getName().indexOf("Emetr") != -1) ) {
            bleSearch.stopScan();
            bleConnector = new BLEConnector(getApplicationContext(), device, this);
            bleConnector.connect();
        }
    }

    @Override
    public void connectedCallback(List<BluetoothGattService> services) {

    }

    @Override
    public void disconnectedCallback() {

    }

    @Override
    public void writeCharCallback() {

    }

    @Override
    public void readCharCallback(BluetoothGattCharacteristic characteristic) {

    }

    @Override
    public void notificationCallback(BluetoothGattCharacteristic characteristic) {

    }

    @Override
    public void operationFailed(BLEConnector.OPERATIONS operation) {

    }
}