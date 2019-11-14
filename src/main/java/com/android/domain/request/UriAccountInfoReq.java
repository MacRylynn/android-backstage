package com.android.domain.request;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UriAccountInfoReq
 * @Description 账号管理公共请求类，为了在前后端传输不使用实体类，所以使用传输层
 * @Author: lijiao73
 * @Date: 2019/11/14 09:22
 */
public class UriAccountInfoReq implements Serializable {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户编号(系统内部使用)
     */
    private String userCode;

    /**
     * 用户账号（唯一）
     */
    private String accountNo;

    /**
     * 用户名
     */
    private String accountName;

    /**
     * 密码
     */
    private String accountPassword;

    /**
     * 注册时间
     */
    private Date registrationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword == null ? null : accountPassword.trim();
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", accountName=").append(accountName);
        sb.append(", accountPassword=").append(accountPassword);
        sb.append(", registrationTime=").append(registrationTime);
        sb.append("]");
        return sb.toString();
    }
}