package com.lee.android.dao;

import com.lee.android.entity.UserInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
//     用pom里面的Spring的spring-boot-starter-test去跑
//     UserInfoDao接口的实现
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoDaoTest {
    //     测试类来检测这些方法是否成功
    //    @Autowired  希望Spring动态的将UserInfoDao的mybatis的实现类注入进来

    @Autowired
    public UserInfoDao userInfoDao;//申明一个UserInfoDao接口

    @Test
    @Ignore
    public void queryUserInfo() {
        List<UserInfo>userInfoList = userInfoDao.queryUserInfo();
        assertEquals(6,userInfoList.size());//期望值返回是6
    }
    @Test
    @Ignore
    public void queryUserInfoById() {
        UserInfo userinfo = userInfoDao.queryUserInfoById(40);
        assertEquals("2222",userinfo.getUserName());
    }

    @Test
    @Ignore
    public void insertUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("asdasd");
        userInfo.setUserPassword("5555555");
        int effectedNum = userInfoDao.insertUserInfo(userInfo);//根据返回的值来确定是否成功插入
        assertEquals(1,effectedNum);//判断后面的值是否和前面相等
    }

    @Test
    @Ignore
    public void updateUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        userInfo.setUserName("ASDASDvvvv");
        userInfo.setUserPassword("sadda222");
        int effectedNum = userInfoDao.updateUserInfo(userInfo);
        assertEquals(1,effectedNum);

    }

    @Test
    @Ignore
    public void deleteUserInfo() {
        int effectedNum = userInfoDao.deleteUserInfo(3);
        assertEquals(1,effectedNum);
    }
}