package com.lxl.mineutil.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/14.
 */

public class FirstResult {

    /**
     * result : 1
     * packet : {"jsonData":"[{\"industryVersion\":\"4\"}]","CloudDate":20170513153456521}
     * version : 1.0
     */

    private int result;
    private PacketBean packet;
    private String version;
    private List<JsonDataBean> jsonData;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public PacketBean getPacket() {
        return packet;
    }

    public void setPacket(PacketBean packet) {
        this.packet = packet;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<JsonDataBean> getJsonData() {
        return jsonData;
    }

    public void setJsonData(List<JsonDataBean> jsonData) {
        this.jsonData = jsonData;
    }

    public static class PacketBean {
        /**
         * jsonData : [{"industryVersion":"4"}]
         * CloudDate : 20170513153456521
         */

        private String jsonData;

        public String getJsonData() {
            return jsonData;
        }

        public void setJsonData(String jsonData) {
            this.jsonData = jsonData;
        }

        private long CloudDate;


        public long getCloudDate() {
            return CloudDate;
        }

        public void setCloudDate(long CloudDate) {
            this.CloudDate = CloudDate;
        }
    }


    public static class JsonDataBean {
        /**
         * industryVersion : 4
         */

        private String industryVersion;

        public String getIndustryVersion() {
            return industryVersion;
        }

        public void setIndustryVersion(String industryVersion) {
            this.industryVersion = industryVersion;
        }
    }
}
