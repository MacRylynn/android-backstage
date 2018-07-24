package com.lee.android.service.impl;


/**
 * 这里是UserTestResultService接口的实现类
 * 和dao实现类一样，然后再到ut测试类里面调用
 */


import com.lee.android.dao.UserTestResultDao;
import com.lee.android.entity.UserTestResult;
import com.lee.android.service.UserTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserTestResultServiceImpl implements UserTestResultService {
    @Autowired
    private UserTestResultDao userTestResultDao;

    @Override
    public List<UserTestResult> getUserTestResultList() {
        return userTestResultDao.queryUserTestResult();
    }

    @Override
    public UserTestResult getUserTestResultById(int testResultId) {
        return userTestResultDao.queryUserTestResultById(testResultId);
    }

    //@Transactional表示事物的控制 默认的是只接受RuntimeException并且回滚
    @Transactional
    @Override
    public boolean addUserTestResult(UserTestResult userTestResult) throws RuntimeException {
        if (userTestResult.getTestResultUser() != null && !"".equals(userTestResult.getTestResultUser())) {
            try {
                int effectedNum = userTestResultDao.insertUserTestResult(userTestResult);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("插入数据失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("插入数据失败:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("用户信息不能为空!");
        }
    }

    @Override
    public boolean modifyUserTestResult(UserTestResult userTestResult) {
        if (userTestResult.getTestResultId() > 0) {
            try {
                int effectedNum = userTestResultDao.updateUserTestResult(userTestResult);
                if (effectedNum > 0) {
                    //  成功插入则返回true
                    return true;
                } else {
                    //  否则返回插入失败
                    throw new RuntimeException("更显数据失败!");
                }
            } catch (Exception e) {
                //try失败做出的反应
                throw new RuntimeException("更新用户数据失败:" + e.toString());
            }
        } else {
            //如果条件不满足则抛出下面的异常
            throw new RuntimeException("用户信息不能为空!");
        }
    }


    @Override
    public boolean deleteUserTestResult(int testResultId) {
        if (testResultId > 0) {
            try {
                int effectedNum = userTestResultDao.deleteUserTestResult(testResultId);

                if (effectedNum > 0) {
                    //  成功插入则返回true
                    return true;
                } else {
                    //  否则返回插入失败
                    throw new RuntimeException("删除数据失败!");
                }
            } catch (Exception e) {
                //try失败做出的反应
                throw new RuntimeException("删除数据失败:" + e.toString());
            }
        } else {
            //如果条件不满足则抛出下面的异常
            throw new RuntimeException("用户Id信息不能为空!");
        }
    }
}
