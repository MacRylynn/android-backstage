package com.android.service.impl;

import com.android.common.mapper.UriAccountInfoMapper;
import com.android.common.mapper.UriCheckResultMapper;
import com.android.common.mapper.UriUserInfoMapper;
import com.android.common.mapper.UriUserRelationMapper;
import com.android.domain.request.UriAccountInfoReq;
import com.android.domain.response.UriAccountInfoVo;
import com.android.domain.response.UriUserInfoVo;
import com.android.service.UriUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UriUserServiceImpl
 * @Description
 * @Author: lijiao73
 * @Date: 2019/11/13 17:13
 */
@Service
public class UriUserServiceImpl implements UriUserService {

    private static final Logger logger = LoggerFactory.getLogger(UriUserServiceImpl.class);

    @Resource
    private UriAccountInfoMapper uriAccountInfoMapper;

    @Resource
    private UriCheckResultMapper uriCheckResultMapper;

    @Resource
    private UriUserInfoMapper uriUserInfoMapper;

    @Resource
    private UriUserRelationMapper uriUserRelationMapper;

    @Override
    public Boolean addAccount(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|addAccount,用户管理服务|添加账号，参数为：{}", req.toString());
        return false;
    }

    @Override
    public Boolean accountLogin(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|accountLogin,用户管理服务|用户登录，参数为：{}", req.toString());
        return false;
    }

    @Override
    public UriAccountInfoVo queryAccountById(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|queryAccountById,用户管理服务|根据Id查询账户，参数为：{}", req.toString());
        return null;
    }

    @Override
    public List<UriAccountInfoVo> queryAllCount() {
        logger.info("UriUserServiceImpl|queryAllCount,用户管理服务|查询所有账户");
        return null;
    }

    @Override
    public List<UriUserInfoVo> queryUserByAccountId(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|queryUserByAccountId,用户管理服务|通过账户Id查询所有用户，参数为：{}", req.toString());
        return null;
    }

    @Override
    public Boolean modifyAccount(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|modifyAccount,用户管理服务|修改账户信息，参数为：{}", req.toString());
        return null;
    }
}
