package com.lee.android.service.impl;

import com.lee.android.entity.UserInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceImplTest {
    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;
    @Test
    @Ignore
    public void getUserInfoList() {
        List<UserInfo>userInfoList = userInfoServiceImpl.getUserInfoList();
        assertEquals(8,userInfoList.size());
    }

    @Test
    @Ignore
    public void getUserInfoById() {
    }

    @Test
    @Ignore
    public void addUserInfo() {
    }

    @Test
    @Ignore
    public void modifyUserInfo() {
    }

    @Test
    @Ignore
    public void deleteUserInfo() {
    }
}