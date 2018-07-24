package com.lee.android.service;


import com.lee.android.entity.UserInfo;

import java.util.List;

//这里是servive的接口，和dao接口一样
public interface UserInfoService {
    List<UserInfo> getUserInfoList(); //获取用户信息列表

    UserInfo getUserInfoById(int areaId);//通过ID获取区域信息

    boolean addUserInfo(UserInfo userInfo) throws RuntimeException;//增加用户信息

    boolean modifyUserInfo(UserInfo userInfo);//修改用户信息

    boolean deleteUserInfo(int areaId);//删除用户信息
}
