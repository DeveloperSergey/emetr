package com.example.emetr;

public class Settings {

    protected short structType;
    protected short cmd;
    protected short gate;
    protected short toneArm;

    Settings(){}
    Settings(int structType, int cmd, int gate, int toneArm){
        this.structType = (short)(structType & 0xFFFF);
        this.cmd = (short)(cmd & 0xFFFF);
        this.gate = (short)(gate & 0xFFFF);
        this.toneArm = (short)(toneArm & 0xFFFF);
    }
    Settings(byte[] bytes){
        this.structType = (short)(bytes[0] | (bytes[1] << 8));
        this.cmd = (short)(bytes[0] | (bytes[1] << 8));
        this.gate = (short)(bytes[0] | (bytes[1] << 8));
        this.toneArm = (short)(bytes[0] | (bytes[1] << 8));
    }

    public byte[] getBytes(){
        byte[] bytes = {
                (byte)(this.structType & 0xFF), (byte)((this.structType >> 8) & 0xFF),
                (byte)(this.cmd & 0xFF), (byte)((this.cmd >> 8) & 0xFF),
                (byte)(this.gate & 0xFF), (byte)((this.gate >> 8) & 0xFF),
                (byte)(this.toneArm & 0xFF), (byte)((this.toneArm >> 8) & 0xFF),
                (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00
        };
        return bytes;
    }
}