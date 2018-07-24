package com.lee.android.service.impl;

import com.lee.android.dao.UserInfoDao;
import com.lee.android.entity.UserInfo;
import com.lee.android.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**这里是UserInfoService接口的实现类
 * 和dao实现类一样，然后再到ut测试类里面调用*/

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public List<UserInfo> getUserInfoList() {
        return userInfoDao.queryUserInfo();//得到用户信息列表
    }


    @Override
    public UserInfo getUserInfoById(int areaId) {
        return userInfoDao.queryUserInfoById(areaId);//用id来获取用户信息
    }


    //@Transactional表示事物的控制 默认的是只接受RuntimeException并且回滚
    @Transactional
    @Override
    public boolean addUserInfo(UserInfo userInfo) throws RuntimeException {
        //根据条件判断 UserName不为空
        if(userInfo.getUserName()!=null && !"".equals(userInfo.getUserName())){
           try {
               int effectedNum =userInfoDao.insertUserInfo(userInfo);//插入用户数据的方法
               if (effectedNum>0){
                   //  成功插入则返回true
                   return true;
               }else {
                   //  否则返回插入失败
                   throw new RuntimeException("插入用户信息失败!");
               }
           }catch (Exception e){
               //try失败做出的反应
               throw new RuntimeException("插入用户信息失败:" + e.getMessage());
           }
        }else {
            //如果条件不满足则抛出下面的异常
            throw new RuntimeException("用户信息不能为空!");
        }
    }



    @Override
    public boolean modifyUserInfo(UserInfo userInfo) {

        //空值判断，主要是areaId不能为空
        if(userInfo.getUserId() > 0){
            try {
                int effectedNum =userInfoDao.updateUserInfo(userInfo);//修改用户数据的方法
                if (effectedNum>0){
                    //  成功插入则返回true
                    return true;
                }else {
                    //  否则返回插入失败
                    throw new RuntimeException("更显用户信息失败!");
                }
            }catch (Exception e){
                //try失败做出的反应
                throw new RuntimeException("更新用户信息失败:" + e.toString());
            }
        }else {
            //如果条件不满足则抛出下面的异常
            throw new RuntimeException("用户信息不能为空!");
        }
    }

    @Override
    public boolean deleteUserInfo(int areaId) {
        if(areaId>0){
            try{
                int effectedNum =userInfoDao.deleteUserInfo(areaId);//删除用户数据的方法
                if (effectedNum>0){
                    //  成功插入则返回true
                    return true;
                }else {
                    //  否则返回插入失败
                    throw new RuntimeException("删除用户信息失败!");
                }
            }catch (Exception e){
                //try失败做出的反应
                throw new RuntimeException("删除用户信息失败:" + e.toString());
            }
        }else {
            //如果条件不满足则抛出下面的异常
            throw new RuntimeException("用户Id信息不能为空!");
        }
    }
}
