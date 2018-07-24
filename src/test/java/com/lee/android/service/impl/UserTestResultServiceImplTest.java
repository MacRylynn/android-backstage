package com.lee.android.service.impl;

import com.lee.android.entity.UserTestResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestResultServiceImplTest {
    @Autowired
    private UserTestResultServiceImpl userTestResultServiceImpl;

    @Test
    public void getUserTestResultList() {
        List<UserTestResult> userTestResultsList= userTestResultServiceImpl.getUserTestResultList();
        assertEquals(3,userTestResultsList.size());
    }

    @Test
    public void getUserTestResultById() {
    }

    @Test
    public void addUserTestResult() {
    }

    @Test
    public void modifyUserTestResult() {
    }

    @Test
    public void deleteUserTestResult() {
        boolean effectedNum = userTestResultServiceImpl.deleteUserTestResult(3);
        assertEquals(effectedNum,true);

    }
}