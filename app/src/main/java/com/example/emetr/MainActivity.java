package com.example.emetr;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements  BLESearch.BLESearchCallback, BLEConnector.BLEConnectorCallbacks{

    BLESearch bleSearch;
    BLEConnector bleConnector;
    Emetr emetr;

    final String svUUID = "0000fff0-0000-1000-8000-00805f9b34fb";
    final String svcFactoryUUID = "0000fff1-0000-1000-8000-00805f9b34fb";   // Read
    final String svcPowerUUID = "0000fff2-0000-1000-8000-00805f9b34fb";     // Read
    final String svcSettingsUUID = "0000fff3-0000-1000-8000-00805f9b34fb";  // Read/Write
    final String svcResultUUID = "0000fff4-0000-1000-8000-00805f9b34fb";    // Read/Notify

    /* Description
    typedef struct{ // Factory
	u16 struct_ver;
	u16 hardware_ver;
	u16 scheme_ver;
	u16 firmware_ver;
	u16 res1;
	u16 res2;
	u16 res3;
	u16 res4;
	u16 res5;
	u16 res6;
} t_factory;

typedef struct{ // Power
	u16 struct_ver;
	u16 vbus;
	u16 vbat;
	u16 v3_3;
	u16 chrg_state;
	u16 btn_state;
	struct{
		u16 percect;
		u16 is_charging;
		u16 discharged;
	}battery;
	u16 res1;
} t_power;

typedef struct{ // Settings
	u16 struct_ver;
	u16 cmd;
	u16 gain;
	u16 tone_arm;
	u16 res1;
	u16 res2;
	u16 res3;
	u16 res4;
	u16 res5;
	u16 res6;
} t_settings;

typedef struct{ // Result
	u16 struct_ver;
	u16 dac1;
	u16 dac2;
	i16 adc_code;
	u16 resistance;
	u16 tone_arm;
	u16 tone;
	u16 gain;
	u16 res1;
	u16 res2;
} t_result;



typedef enum{
	CMD_GO_TO_SET,
	CMD_SETTINGS
} e_commands;

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ScreenView screenView = new ScreenView(getApplicationContext());
        setContentView(screenView);
        emetr = new Emetr(screenView);
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

        // Read Factory
        BluetoothGattService service = bleConnector.bleGatt.getService(UUID.fromString(svUUID));
        BluetoothGattCharacteristic charFactory = service.getCharacteristic(UUID.fromString(svcFactoryUUID));
        bleConnector.readChar(charFactory);

        // Enable notifications
        bleConnector.notiEnable(svUUID, svcResultUUID);
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

        if(characteristic.getUuid().toString().equals(svcResultUUID)){

            int value = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 6);
            //Log.d("Main", String.valueOf(value));
            emetr.setTone((float)value);
        }

    }

    @Override
    public void operationFailed(BLEConnector.OPERATIONS operation) {

    }
}