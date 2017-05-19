package com.lxl.mineutil.bean;

/**
 * Created by lxl on 2017/5/16.
 */

public class BindPhonePacket {

    /**
     * jsonData : {"deviceInfo":{"OSVersion":"6.0","Operator":"未知","Phone":"000000","Model":"GEM-703L","Resolution":"1848:1200","NetType":"WIFI","OS":"23","Name":"HUAWEIGEM-703L"},"deviceId":"000000000000000","type":1,"phoneCode":"13126445293"}
     */

    private String jsonData;

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }







    public static class JsonDataBean {
        /**
         * deviceInfo : {"OSVersion":"6.0","Operator":"未知","Phone":"000000","Model":"GEM-703L","Resolution":"1848:1200","NetType":"WIFI","OS":"23","Name":"HUAWEIGEM-703L"}
         * deviceId : 000000000000000
         * type : 1
         * phoneCode : 13126445293
         */

        private DeviceInfoBean deviceInfo;
        private int type;
        private String phoneCode;
        private String deviceId;


        public DeviceInfoBean getDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(DeviceInfoBean deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPhoneCode() {
            return phoneCode;
        }

        public void setPhoneCode(String phoneCode) {
            this.phoneCode = phoneCode;
        }

        public static class DeviceInfoBean {
            /**
             * OSVersion : 6.0
             * Operator : 未知
             * Phone : 000000
             * Model : GEM-703L
             * Resolution : 1848:1200
             * NetType : WIFI
             * OS : 23
             * Name : HUAWEIGEM-703L
             */

            private String OSVersion;
            private String Operator;
            private String Phone;
            private String Model;
            private String Resolution;
            private String NetType;
            private String OS;
            private String Name;

            public String getOSVersion() {
                return OSVersion;
            }

            public void setOSVersion(String OSVersion) {
                this.OSVersion = OSVersion;
            }

            public String getOperator() {
                return Operator;
            }

            public void setOperator(String Operator) {
                this.Operator = Operator;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getModel() {
                return Model;
            }

            public void setModel(String Model) {
                this.Model = Model;
            }

            public String getResolution() {
                return Resolution;
            }

            public void setResolution(String Resolution) {
                this.Resolution = Resolution;
            }

            public String getNetType() {
                return NetType;
            }

            public void setNetType(String NetType) {
                this.NetType = NetType;
            }

            public String getOS() {
                return OS;
            }

            public void setOS(String OS) {
                this.OS = OS;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }
        }
    }
}
