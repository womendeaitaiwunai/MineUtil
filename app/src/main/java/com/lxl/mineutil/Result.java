package com.lxl.mineutil;




public class Result {

    /**
     * result : 1
     * packet : {"jsonData":"{\"result\":1,\"timeZoneID\":\"Asia/Shanghai\",\"time\":1494654971607,\"dataGUID\":\"378a868b-7849-4603-82bb-ec4a63072adc\"}","CloudDate":20170513135611608}
     * version : 1.0
     */

    private int result;
    private PacketBean packet;
    private String version;
    /**
     * jsonData : {"result":1,"timeZoneID":"Asia/Shanghai","time":1494654971607,"dataGUID":"378a868b-7849-4603-82bb-ec4a63072adc"}
     */

    private JsonDataBean jsonData;

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

    public JsonDataBean getJsonData() {
        return jsonData;
    }

    public void setJsonData(JsonDataBean jsonData) {
        this.jsonData = jsonData;
    }

    public static class PacketBean {
        /**
         * jsonData : {"result":1,"timeZoneID":"Asia/Shanghai","time":1494654971607,"dataGUID":"378a868b-7849-4603-82bb-ec4a63072adc"}
         * CloudDate : 20170513135611608
         */

        private JsonDataBean jsonData;
        private long CloudDate;

        public JsonDataBean getJsonData() {
            return jsonData;
        }

        public void setJsonData(JsonDataBean jsonData) {
            this.jsonData = jsonData;
        }

        public long getCloudDate() {
            return CloudDate;
        }

        public void setCloudDate(long CloudDate) {
            this.CloudDate = CloudDate;
        }
    }


    public static class JsonDataBean {
        /**
         * result : 1
         * timeZoneID : Asia/Shanghai
         * time : 1494654971607
         * dataGUID : 378a868b-7849-4603-82bb-ec4a63072adc
         */
        private int resultX;
        private String timeZoneID;
        private long time;
        private String dataGUID;

        public int getResultX() {
            return resultX;
        }

        public void setResultX(int resultX) {
            this.resultX = resultX;
        }

        public String getTimeZoneID() {
            return timeZoneID;
        }

        public void setTimeZoneID(String timeZoneID) {
            this.timeZoneID = timeZoneID;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getDataGUID() {
            return dataGUID;
        }

        public void setDataGUID(String dataGUID) {
            this.dataGUID = dataGUID;
        }
    }
}
