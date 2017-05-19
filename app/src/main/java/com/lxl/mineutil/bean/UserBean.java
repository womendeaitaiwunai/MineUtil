package com.lxl.mineutil.bean;

import java.util.List;

/**
 * Created by lxl on 2017/5/16.
 */

public class UserBean {

    private List<UserPerimtListBean> userPerimtList;
    private List<EnterpriseUserListBean> enterpriseUserList;
    private List<EnterpriseListBean> enterpriseList;

    public List<UserPerimtListBean> getUserPerimtList() {
        return userPerimtList;
    }

    public void setUserPerimtList(List<UserPerimtListBean> userPerimtList) {
        this.userPerimtList = userPerimtList;
    }

    public List<EnterpriseUserListBean> getEnterpriseUserList() {
        return enterpriseUserList;
    }

    public void setEnterpriseUserList(List<EnterpriseUserListBean> enterpriseUserList) {
        this.enterpriseUserList = enterpriseUserList;
    }

    public List<EnterpriseListBean> getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(List<EnterpriseListBean> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    public static class UserPerimtListBean {
        /**
         * EditTime : 2017-05-15 10:46:03
         * EnterpriseKID : a9c8cf32-1186-4da6-b010-c6826e9336bd
         * DataTimestamp : 2618688398
         * IsDeleted : 0
         * Permit : 1
         * ControlItemDesc : 是否可登录PDA
         * KID : b81e2e2e-0aa4-4611-b936-d17f0e7bae34
         * CreateTime : 2017-05-15 10:46:03
         * UserKID : 4bbb61de-2701-4556-801a-6a41e6ff6941
         * ControlItem : Login_PDA
         * Creator : 708e2a3d-0ec4-49d4-85de-428c212efab7
         * Editor : 708e2a3d-0ec4-49d4-85de-428c212efab7
         */

        private String EditTime;
        private String EnterpriseKID;
        private long DataTimestamp;
        private int IsDeleted;
        private int Permit;
        private String ControlItemDesc;
        private String KID;
        private String CreateTime;
        private String UserKID;
        private String ControlItem;
        private String Creator;
        private String Editor;

        public String getEditTime() {
            return EditTime;
        }

        public void setEditTime(String EditTime) {
            this.EditTime = EditTime;
        }

        public String getEnterpriseKID() {
            return EnterpriseKID;
        }

        public void setEnterpriseKID(String EnterpriseKID) {
            this.EnterpriseKID = EnterpriseKID;
        }

        public long getDataTimestamp() {
            return DataTimestamp;
        }

        public void setDataTimestamp(long DataTimestamp) {
            this.DataTimestamp = DataTimestamp;
        }

        public int getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(int IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public int getPermit() {
            return Permit;
        }

        public void setPermit(int Permit) {
            this.Permit = Permit;
        }

        public String getControlItemDesc() {
            return ControlItemDesc;
        }

        public void setControlItemDesc(String ControlItemDesc) {
            this.ControlItemDesc = ControlItemDesc;
        }

        public String getKID() {
            return KID;
        }

        public void setKID(String KID) {
            this.KID = KID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getUserKID() {
            return UserKID;
        }

        public void setUserKID(String UserKID) {
            this.UserKID = UserKID;
        }

        public String getControlItem() {
            return ControlItem;
        }

        public void setControlItem(String ControlItem) {
            this.ControlItem = ControlItem;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }

        public String getEditor() {
            return Editor;
        }

        public void setEditor(String Editor) {
            this.Editor = Editor;
        }
    }

    public static class EnterpriseUserListBean {
        /**
         * EditTime : 2017-05-15 10:21:23
         * DataTimestamp : 2618568422
         * IsDeleted : 0
         * UserLogo : null
         * KID : 708e2a3d-0ec4-49d4-85de-428c212efab7
         * CreateTime : 2017-05-15 10:21:23
         * Creator : Lenx
         * Name : 好
         * Role : 1
         * EnterpriseKID : a9c8cf32-1186-4da6-b010-c6826e9336bd
         * Phone : 13126445293
         * Editor : Lenx
         * Password : 25D55AD283AA400AF464C76D713C07AD
         */

        private String EditTime;
        private long DataTimestamp;
        private int IsDeleted;
        private Object UserLogo;
        private String KID;
        private String CreateTime;
        private String Creator;
        private String Name;
        private int Role;
        private String EnterpriseKID;
        private String Phone;
        private String Editor;
        private String Password;

        public String getEditTime() {
            return EditTime;
        }

        public void setEditTime(String EditTime) {
            this.EditTime = EditTime;
        }

        public long getDataTimestamp() {
            return DataTimestamp;
        }

        public void setDataTimestamp(long DataTimestamp) {
            this.DataTimestamp = DataTimestamp;
        }

        public int getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(int IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public Object getUserLogo() {
            return UserLogo;
        }

        public void setUserLogo(Object UserLogo) {
            this.UserLogo = UserLogo;
        }

        public String getKID() {
            return KID;
        }

        public void setKID(String KID) {
            this.KID = KID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getRole() {
            return Role;
        }

        public void setRole(int Role) {
            this.Role = Role;
        }

        public String getEnterpriseKID() {
            return EnterpriseKID;
        }

        public void setEnterpriseKID(String EnterpriseKID) {
            this.EnterpriseKID = EnterpriseKID;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getEditor() {
            return Editor;
        }

        public void setEditor(String Editor) {
            this.Editor = Editor;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }
    }

    public static class EnterpriseListBean {
        /**
         * EditTime : 2017-05-15 10:19:29
         * CountryID : 1
         * Address : 一巷子
         * ExpireDate : 2017-05-22 10:19:29
         * DistrictID : 414
         * Latitude : 0.0
         * Creator : Creator
         * Name : 压缩
         * Remark : 算法
         * Industry : null
         * Phone : 13126445293
         * CityGeographyKID : null
         * Sort : 0
         * ProvinceGeographyKID : null
         * IsDeleted : 0
         * IndustryId : 2
         * LogoImage : null
         * KID : a9c8cf32-1186-4da6-b010-c6826e9336bd
         * CreateTime : 2017-05-15 10:19:29
         * IndustryMainId : 1
         * Code : a9c8cf32-1186-4da6-b010-c6826e9336bd
         * Longitude : 0.0
         * Contact : 李四
         * ProvinceID : 6
         * Header : null
         * CityID : 30
         * Footer : null
         * Tel : 18922358996
         * Fax : null
         * Editor : Editor
         */

        private String EditTime;
        private int CountryID;
        private String Address;
        private String ExpireDate;
        private int DistrictID;
        private double Latitude;
        private String Creator;
        private String Name;
        private String Remark;
        private Object Industry;
        private String Phone;
        private Object CityGeographyKID;
        private int Sort;
        private Object ProvinceGeographyKID;
        private int IsDeleted;
        private int IndustryId;
        private Object LogoImage;
        private String KID;
        private String CreateTime;
        private int IndustryMainId;
        private String Code;
        private double Longitude;
        private String Contact;
        private int ProvinceID;
        private Object Header;
        private int CityID;
        private Object Footer;
        private String Tel;
        private Object Fax;
        private String Editor;

        public String getEditTime() {
            return EditTime;
        }

        public void setEditTime(String EditTime) {
            this.EditTime = EditTime;
        }

        public int getCountryID() {
            return CountryID;
        }

        public void setCountryID(int CountryID) {
            this.CountryID = CountryID;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getExpireDate() {
            return ExpireDate;
        }

        public void setExpireDate(String ExpireDate) {
            this.ExpireDate = ExpireDate;
        }

        public int getDistrictID() {
            return DistrictID;
        }

        public void setDistrictID(int DistrictID) {
            this.DistrictID = DistrictID;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public Object getIndustry() {
            return Industry;
        }

        public void setIndustry(Object Industry) {
            this.Industry = Industry;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public Object getCityGeographyKID() {
            return CityGeographyKID;
        }

        public void setCityGeographyKID(Object CityGeographyKID) {
            this.CityGeographyKID = CityGeographyKID;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public Object getProvinceGeographyKID() {
            return ProvinceGeographyKID;
        }

        public void setProvinceGeographyKID(Object ProvinceGeographyKID) {
            this.ProvinceGeographyKID = ProvinceGeographyKID;
        }

        public int getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(int IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public int getIndustryId() {
            return IndustryId;
        }

        public void setIndustryId(int IndustryId) {
            this.IndustryId = IndustryId;
        }

        public Object getLogoImage() {
            return LogoImage;
        }

        public void setLogoImage(Object LogoImage) {
            this.LogoImage = LogoImage;
        }

        public String getKID() {
            return KID;
        }

        public void setKID(String KID) {
            this.KID = KID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getIndustryMainId() {
            return IndustryMainId;
        }

        public void setIndustryMainId(int IndustryMainId) {
            this.IndustryMainId = IndustryMainId;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double Longitude) {
            this.Longitude = Longitude;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String Contact) {
            this.Contact = Contact;
        }

        public int getProvinceID() {
            return ProvinceID;
        }

        public void setProvinceID(int ProvinceID) {
            this.ProvinceID = ProvinceID;
        }

        public Object getHeader() {
            return Header;
        }

        public void setHeader(Object Header) {
            this.Header = Header;
        }

        public int getCityID() {
            return CityID;
        }

        public void setCityID(int CityID) {
            this.CityID = CityID;
        }

        public Object getFooter() {
            return Footer;
        }

        public void setFooter(Object Footer) {
            this.Footer = Footer;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public Object getFax() {
            return Fax;
        }

        public void setFax(Object Fax) {
            this.Fax = Fax;
        }

        public String getEditor() {
            return Editor;
        }

        public void setEditor(String Editor) {
            this.Editor = Editor;
        }
    }
}
