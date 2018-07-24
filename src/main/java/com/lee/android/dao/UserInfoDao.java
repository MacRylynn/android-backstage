package com.lee.android.dao;

import com.lee.android.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


//需要加注解@Mapper，之后在测试类里面声明接口时Bean才会正确的注入
//里面的具体的方法实现是在mapper的UserInfo.xml里面
@Mapper
public interface UserInfoDao {
    //列出用户的信息列表 @return UserInfoList
    List<UserInfo> queryUserInfo();//返回UserInfo的多条信息

    UserInfo queryUserInfoById(int userId);//查 按照id来查

    int insertUserInfo(UserInfo userInfo);//增

    int updateUserInfo(UserInfo userInfo);//改

    int deleteUserInfo(int userId);//删  按照id来删

}
