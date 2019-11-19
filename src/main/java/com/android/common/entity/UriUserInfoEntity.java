package com.android.common.entity;

import java.util.Date;

public class UriUserInfoEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 用户年龄
     */
    private Integer userAge;

    /**
     * 用户职业
     */
    private String userCareer;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户电话号码
     */
    private String userPhone;

    /**
     * 用户住址
     */
    private String userAddress;

    /**
     * 用户是否有过患病EverSickEnum(YES,是;NO,否)
     */
    private String everUriSick;

    /**
     * 患病时间（若有）
     */
    private Date sickTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserCareer() {
        return userCareer;
    }

    public void setUserCareer(String userCareer) {
        this.userCareer = userCareer == null ? null : userCareer.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getEverUriSick() {
        return everUriSick;
    }

    public void setEverUriSick(String everUriSick) {
        this.everUriSick = everUriSick == null ? null : everUriSick.trim();
    }

    public Date getSickTime() {
        return sickTime;
    }

    public void setSickTime(Date sickTime) {
        this.sickTime = sickTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userAge=").append(userAge);
        sb.append(", userCareer=").append(userCareer);
        sb.append(", userName=").append(userName);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userAddress=").append(userAddress);
        sb.append(", everUriSick=").append(everUriSick);
        sb.append(", sickTime=").append(sickTime);
        sb.append("]");
        return sb.toString();
    }
}