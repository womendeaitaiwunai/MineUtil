package com.lxl.mineutil;

/**
 * Created by lxl on 2017/5/15.
 */

public class SendMsmBean {

    /**
     * msg : 该设备没有在云端注册
     * result : -2
     * version : 5.4.9.021
     */

    private String msg;
    private int result;
    private String version;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
