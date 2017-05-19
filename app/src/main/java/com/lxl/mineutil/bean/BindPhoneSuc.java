package com.lxl.mineutil.bean;

/**
 * Created by lxl on 2017/5/16.
 */

public class BindPhoneSuc {

    /**
     * result : 1
     * packet : {"jsonData":"{\"isInitBoosOpOperator\":true,\"industryId\":\"2\",\"industryMainId\":\"1\",\"enterpriseId\":\"a9c8cf32-1186-4da6-b010-c6826e9336bd\",\"shortCode\":1}","CloudDate":20170516153740938}
     * version : 1.0
     */

    private int result;
    private PacketBean packet;
    private String version;
    /**
     * jsonData : {"isInitBoosOpOperator":true,"industryId":"2","industryMainId":"1","enterpriseId":"a9c8cf32-1186-4da6-b010-c6826e9336bd","shortCode":1}
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
         * jsonData : {"isInitBoosOpOperator":true,"industryId":"2","industryMainId":"1","enterpriseId":"a9c8cf32-1186-4da6-b010-c6826e9336bd","shortCode":1}
         * CloudDate : 20170516153740938
         */

        private String jsonData;
        private long CloudDate;

        public String getJsonData() {
            return jsonData;
        }

        public void setJsonData(String jsonData) {
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
         * isInitBoosOpOperator : true
         * industryId : 2
         * industryMainId : 1
         * enterpriseId : a9c8cf32-1186-4da6-b010-c6826e9336bd
         * shortCode : 1
         */

        private boolean isInitBoosOpOperator;
        private String industryId;
        private String industryMainId;
        private String enterpriseId;
        private int shortCode;

        public boolean isIsInitBoosOpOperator() {
            return isInitBoosOpOperator;
        }

        public void setIsInitBoosOpOperator(boolean isInitBoosOpOperator) {
            this.isInitBoosOpOperator = isInitBoosOpOperator;
        }

        public String getIndustryId() {
            return industryId;
        }

        public void setIndustryId(String industryId) {
            this.industryId = industryId;
        }

        public String getIndustryMainId() {
            return industryMainId;
        }

        public void setIndustryMainId(String industryMainId) {
            this.industryMainId = industryMainId;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public int getShortCode() {
            return shortCode;
        }

        public void setShortCode(int shortCode) {
            this.shortCode = shortCode;
        }
    }
}
