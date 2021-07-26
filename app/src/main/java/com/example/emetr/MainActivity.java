package com.example.emetr;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.emetr.BLE.BLEConnector;
import com.example.emetr.BLE.BLESearch;
import com.example.emetr.BLE.Factory;
import com.example.emetr.Views.ScreenView;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements  BLESearch.BLESearchCallback,
        BLEConnector.BLEConnectorCallbacks{

    BLESearch bleSearch;
    BLEConnector bleConnector;
    Emetr emetr;

    final String svUUID = "0000fff0-0000-1000-8000-00805f9b34fb";
    final String svcFactoryUUID = "0000fff1-0000-1000-8000-00805f9b34fb";   // Read
    final String svcPowerUUID = "0000fff2-0000-1000-8000-00805f9b34fb";     // Read
    final String svcSettingsUUID = "0000fff3-0000-1000-8000-00805f9b34fb";  // Read/Write
    final String svcResultUUID = "0000fff4-0000-1000-8000-00805f9b34fb";    // Read/Notify

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View
        ScreenView screenView = new ScreenView(getApplicationContext(), null);
        setContentView(screenView); //setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

        // Model
        emetr = new Emetr(screenView);

        // Bluetooth
        bleSearch = new BLESearch(this, this);
        bleSearch.startScan();
    }

    /* ------------------------------------------ BLE ------------------------------------------ */
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

        emetr.setConnection(true);

        // Read Factory
        BluetoothGattService service = bleConnector.bleGatt.getService(UUID.fromString(svUUID));
        BluetoothGattCharacteristic charFactory = service.getCharacteristic(UUID.fromString(svcFactoryUUID));
        bleConnector.readChar(charFactory);

        // Enable notifications
        bleConnector.notiEnable(svUUID, svcResultUUID);
    }

    @Override
    public void disconnectedCallback() {
        emetr.setConnection(false);
    }

    @Override
    public void writeCharCallback() {

    }

    @Override
    public void readCharCallback(BluetoothGattCharacteristic characteristic) {
        if(characteristic.getUuid().toString().equals(svcSettingsUUID)) {
            try {
                emetr.setFactory(new Factory(characteristic.getValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notificationCallback(BluetoothGattCharacteristic characteristic) {

        if(characteristic.getUuid().toString().equals(svcResultUUID)){

            int value = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 6);
            Log.d("Main", String.valueOf(value));
            emetr.setToneValue((float)value);
        }

    }

    @Override
    public void operationFailed(BLEConnector.OPERATIONS operation) {

    }
    /* ----------------------------------------------------------------------------------------- */
}