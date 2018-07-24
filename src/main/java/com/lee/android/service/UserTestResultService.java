package com.lee.android.service;


import com.lee.android.entity.UserTestResult;

import java.util.List;

//这里是servive的接口，和dao接口一样
public interface UserTestResultService {
    List<UserTestResult> getUserTestResultList(); //获取用户信息列表

    UserTestResult getUserTestResultById(int testResultId);//通过ID获取区域信息

    boolean addUserTestResult(UserTestResult userTestResult) throws RuntimeException;//增加用户信息

    boolean modifyUserTestResult(UserTestResult userTestResult);//修改用户信息

    boolean deleteUserTestResult(int testResultId);//删除用户信息
}
