package com.example.emetr.BLE;

public class Factory {

    public final short VERSION;
    public final short HARDWARE;
    public final short SCHEME;
    public final short FIRMWARE;

    public Factory(byte[] data) throws Exception {
        VERSION = (short)((data[0]) | (data[1] << 8));
        if(VERSION == 1) {
            HARDWARE =  (short)((data[2] << 8) | (data[3] << 0));
            SCHEME =    (short)((data[4] << 8) | (data[5] << 0));
            FIRMWARE =  (short)((data[6] << 8) | (data[7] << 0));
        }
        else throw new Exception("Factory structure version is unknown!");
    }
}