package com.android.domain.request;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UriUserInfoReq
 * @Description 用户基本信息公共请求类，为了在前后端传输不使用实体类，所以使用传输层
 * @Author: lijiao73
 * @Date: 2019/11/14 09:22
 */
public class UriUserInfoReq implements Serializable {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;
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
    private Date registrationTime;
    /**
     * 关系类型RelationTypeEnum(PARENTS,父母;SELF,自己;BROTHER,兄弟;SISTER,姐妹;CHILDREN,子女;OTHERS,其他)
     */
    private String relationType;

    /**
     * 用户编号(系统内部使用)
     */
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

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

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public String toString() {
        return "UriUserInfoReq{" +
                "id=" + id +
                ", userAge=" + userAge +
                ", userCareer='" + userCareer + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", everUriSick='" + everUriSick + '\'' +
                ", registrationTime=" + registrationTime +
                ", relationType='" + relationType + '\'' +
                ", userCode='" + userCode + '\'' +
                '}';
    }
}