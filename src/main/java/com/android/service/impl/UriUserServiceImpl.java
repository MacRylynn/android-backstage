package com.android.service.impl;

import com.android.common.entity.UriAccountInfoEntity;
import com.android.common.entity.UriUserInfoEntity;
import com.android.common.entity.UriUserRelationEntity;
import com.android.common.exception.BusinessException;
import com.android.common.mapper.UriAccountInfoMapper;
import com.android.common.mapper.UriUserInfoMapper;
import com.android.common.mapper.UriUserRelationMapper;
import com.android.domain.request.UriAccountInfoReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriAccountInfoVo;
import com.android.domain.response.UriUserInfoVo;
import com.android.service.UriUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    private UriUserInfoMapper uriUserInfoMapper;

    @Resource
    private UriUserRelationMapper uriUserRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addAccount(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|addAccount,用户管理服务|添加账号，参数为：{}", req.toString());
        //给每个用户生成一个userCode
        String userCode = "ACCOUNT" + System.currentTimeMillis();
        UriAccountInfoEntity uriAccountInfoEntity = new UriAccountInfoEntity();
        BeanUtils.copyProperties(req, uriAccountInfoEntity);
        uriAccountInfoEntity.setRegistrationTime(new Date());
        uriAccountInfoEntity.setUserCode(userCode);
        int res = uriAccountInfoMapper.insertSelective(uriAccountInfoEntity);
        return res == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addRelationUser(UriUserInfoReq req) {
        logger.info("UriUserServiceImpl|addRelationUser,用户管理服务|添加相关用户，参数为：{}", req.toString());
        //1. 添加用户信息记录
        UriUserInfoEntity uriUserInfoEntity = new UriUserInfoEntity();
        BeanUtils.copyProperties(req, uriUserInfoEntity);
        int resultInfo = uriUserInfoMapper.insertSelective(uriUserInfoEntity);
        //2. 添加账户和用户关联记录
        UriUserRelationEntity uriUserRelationEntity = new UriUserRelationEntity();
        uriUserRelationEntity.setRelationType(req.getRelationType());
        uriUserRelationEntity.setUserCode(req.getUserCode());
        uriUserRelationEntity.setUserId(uriUserInfoEntity.getId());
        int resRelation = uriUserRelationMapper.insertSelective(uriUserRelationEntity);
        return (resultInfo == 1) && (resRelation == 1);
    }

    @Override
    public Long accountLogin(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|accountLogin,用户管理服务|用户登录，参数为：{}", req.toString());
        UriAccountInfoEntity uriAccountInfoEntity = uriAccountInfoMapper.selectByAccountNo(req.getAccountNo());
        if (uriAccountInfoEntity == null) {
            logger.error("UriUserServiceImpl|accountLogin,用户管理服务|用户登录，未找到此用户，参数为：{}", req.toString());
            throw new BusinessException("登陆失败，请验证账号是否存在或者账户输入是否正确");
        }
        if (req.getAccountPassword().equals(uriAccountInfoEntity.getAccountPassword())) {
            logger.info("UriUserServiceImpl|accountLogin,用户管理服务|用户登录，成功登陆，参数为：{}", req.toString());
            return uriAccountInfoEntity.getId();
        } else {
            logger.error("UriUserServiceImpl|accountLogin,用户管理服务|用户登录，登陆密码错误，参数为：{}", req.toString());
            throw new BusinessException("登陆失败，请验证密码输入是否正确");
        }

    }

    @Override
    public UriAccountInfoVo queryAccountById(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|queryAccountById,用户管理服务|根据Id查询账户，参数为：{}", req.toString());
        UriAccountInfoVo uriAccountInfoVo = new UriAccountInfoVo();
        UriAccountInfoEntity uriAccountInfoEntity = uriAccountInfoMapper.selectByPrimaryKey(req.getId());
        if (uriAccountInfoEntity != null) {
            BeanUtils.copyProperties(uriAccountInfoEntity, uriAccountInfoVo);
        } else {
            logger.error("UriUserServiceImpl|queryAccountById,用户管理服务|根据Id查询账户，未找到此用户，参数为：{}", req.toString());
            throw new BusinessException("查询失败，请查询传入参数是否正确");
        }
        return uriAccountInfoVo;
    }

    @Override
    public List<UriAccountInfoVo> queryAllACount() {
        logger.info("UriUserServiceImpl|queryAllCount,用户管理服务|查询所有账户");
        List<UriAccountInfoVo> res = new ArrayList<>();
        List<UriAccountInfoEntity> resList = uriAccountInfoMapper.selectAll();
        resList.forEach(entity -> {
            UriAccountInfoVo uriAccountInfoVo = new UriAccountInfoVo();
            BeanUtils.copyProperties(entity, uriAccountInfoVo);
            res.add(uriAccountInfoVo);
        });
        return res;
    }

    @Override
    public List<UriUserInfoVo> queryUserByAccountId(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|queryUserByAccountId,用户管理服务|通过账户Id查询账户下所有用户，参数为：{}", req.toString());
        List<UriUserInfoVo> res = new ArrayList<>();
        UriAccountInfoEntity uriAccountInfoEntity = uriAccountInfoMapper.selectByPrimaryKey(req.getId());
        if (uriAccountInfoEntity == null) {
            logger.error("UriUserServiceImpl|queryUserByAccountId,用户管理服务|通过账户Id查询账户下所有用户，参数为：{}", req.toString());
            throw new BusinessException("查询失败，请查询传入参数是否正确");
        }
        List<UriUserRelationEntity> resList = uriUserRelationMapper.selectByUserCode(uriAccountInfoEntity.getUserCode());
        resList.forEach(entity -> {
            UriUserInfoVo uriUserInfoVo = new UriUserInfoVo();
            UriUserInfoEntity uriUserInfoEntity = uriUserInfoMapper.selectByPrimaryKey(entity.getUserId());
            BeanUtils.copyProperties(uriUserInfoEntity, uriUserInfoVo);
            uriUserInfoVo.setRelationType(entity.getRelationType());
            res.add(uriUserInfoVo);
        });
        return res;
    }

    @Override
    public Boolean modifyAccount(UriAccountInfoReq req) {
        logger.info("UriUserServiceImpl|modifyAccount,用户管理服务|修改账户信息，参数为：{}", req.toString());
        UriAccountInfoEntity uriAccountInfoEntity = new UriAccountInfoEntity();
        BeanUtils.copyProperties(req, uriAccountInfoEntity);
        int res = uriAccountInfoMapper.updateByPrimaryKeySelective(uriAccountInfoEntity);
        return res == 1;
    }
}
