package com.example.emetr.BLE;

public class Factory {

    public final short VERSION;
    public final short HARDWARE;
    public final short SCHEME;
    public final short FIRMWARE;

    public Factory(byte[] data) throws Exception {
        VERSION = (short)((data[0]) | (data[1] << 8));
        if(VERSION == 1) {
            HARDWARE =  (short)((data[2]) | (data[3] << 8));
            SCHEME =    (short)((data[4]) | (data[5] << 8));
            FIRMWARE =  (short)((data[6]) | (data[7] << 8));
        }
        else throw new Exception("Factory structure version is unknown!");
    }
}