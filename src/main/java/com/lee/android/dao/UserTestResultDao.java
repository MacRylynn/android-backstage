package com.lee.android.dao;

import com.lee.android.entity.UserTestResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//在写完mapper层的.xml时如果没有加注解，就会没有bean注入
@Mapper
public interface UserTestResultDao {
    //列出用户的信息列表 @return UserTestResultList
    List<UserTestResult> queryUserTestResult();//返回UserTestResult的多条信息

    UserTestResult queryUserTestResultById(int testResultId);//查 按照id来查

    int insertUserTestResult(UserTestResult userTestResult);//增

    int updateUserTestResult(UserTestResult userTestResult);//改

    int deleteUserTestResult(int testResultId);//删  按照id来删
}
