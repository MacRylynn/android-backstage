package com.lee.android.dao;

import com.lee.android.entity.UserTestResult;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


//     用pom里面的Spring的spring-boot-starter-test去跑
//     UserTestResultDao接口的实现
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestResultDaoTest {

    @Autowired
    public UserTestResultDao userTestResultDao;


    //测试返回数据库的所有值的列表
    @Test
    @Ignore
    public void queryUserTestResult() {
        List<UserTestResult> userTestResultsList = userTestResultDao.queryUserTestResult();
        assertEquals(3, userTestResultsList.size());//期望值返回是3

    }

    //测试通过id来访问数据库
    @Test
    @Ignore
    public void queryUserTestResultById() {
        UserTestResult userTestResult = userTestResultDao.queryUserTestResultById(3);
        assertEquals("sdf", userTestResult.getTestResultUser());
    }

    //测试增加数据库的内容
    @Test
    @Ignore
    public void insertUserTestResult() {
        UserTestResult userTestResult = new UserTestResult();
        userTestResult.setTestResultUser("dasd");
        userTestResult.setTestResult("a22212121");
        userTestResult.setTestImage("asd");
        int effectedNum = userTestResultDao.insertUserTestResult(userTestResult);//根据返回的值来确定是否成功插入
        assertEquals(1, effectedNum);//判断后面的值是否和前面相等
    }

    //测试修改数据库的内容
    @Test
    @Ignore
    public void updateUserTestResult() {
        UserTestResult userTestResult = new UserTestResult();
        userTestResult.setTestResultId(2);
        userTestResult.setTestResultUser("ADMIN");
        int effectedNum = userTestResultDao.updateUserTestResult(userTestResult);
        assertEquals(1, effectedNum);
    }

    //测试删除数据库的内容
    @Test
    @Ignore
    public void deleteUserTestResult() {
        int effectedNum = userTestResultDao.deleteUserTestResult(4);
        assertEquals(1, effectedNum);
    }
}