package com.entity;

/**
 * Created by Luong Vien on 03.03.2018.
 */

public class Member {
    private String deviceID;
    private String keyID;
    private String email;
    private String sdt;
    public Member(){

    }

    public Member(String deviceID, String keyID, String email, String sdt){
        this.deviceID = deviceID;
        this.keyID = keyID;
        this.email = email;
        this.sdt = sdt;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
