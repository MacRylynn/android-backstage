package com.lee.android.entity;


/** CREATED BY LJ 2018/6/15  version 1.0 */

public class UserInfo {

    /**数据库中的用户名和密码的表
     userId是用户的id
     userName是用户名
     userPassword是用户密码
     getter和setter方法为自动生成*/
    private Integer userId;
    private String userName;
    private String userPassword;



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
