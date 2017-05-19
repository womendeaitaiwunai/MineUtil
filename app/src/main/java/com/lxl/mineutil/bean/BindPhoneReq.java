package com.lxl.mineutil.bean;

/**
 * Created by lxl on 2017/5/16.
 */

public class BindPhoneReq {

    /**
     * mid : 000000000000000
     * accesstoken : 10601716102549057
     * packet : {"jsonData":"{\"deviceInfo\":{\"OSVersion\":\"6.0\",\"Operator\":\"未知\",\"Phone\":\"000000\",\"Model\":\"GEM-703L\",\"Resolution\":\"1848:1200\",\"NetType\":\"WIFI\",\"OS\":\"23\",\"Name\":\"HUAWEIGEM-703L\"},\"deviceId\":\"000000000000000\",\"type\":1,\"phoneCode\":\"13126445293\"}"}
     * version : 5.4.9.021
     */

    private String mid;
    private String accesstoken;
    private BindPhonePacket packet;
    private String version;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public BindPhonePacket getPacket() {
        return packet;
    }

    public void setPacket(BindPhonePacket packet) {
        this.packet = packet;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
