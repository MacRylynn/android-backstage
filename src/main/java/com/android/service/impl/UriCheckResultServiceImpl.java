package com.android.service.impl;

import com.android.common.entity.UriCheckResultEntity;
import com.android.common.mapper.UriAccountInfoMapper;
import com.android.common.mapper.UriCheckResultMapper;
import com.android.common.mapper.UriUserInfoMapper;
import com.android.common.mapper.UriUserRelationMapper;
import com.android.domain.request.UriCheckResultReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriCheckResultVo;
import com.android.service.UriCheckResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UriCheckResultServiceImpl
 * @Description
 * @Author: lijiao73
 * @Date: 2019/11/13 17:13
 */
@Service
public class UriCheckResultServiceImpl implements UriCheckResultService {

    private static final Logger logger = LoggerFactory.getLogger(UriCheckResultServiceImpl.class);

    @Resource
    private UriCheckResultMapper uriCheckResultMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCheckResult(UriCheckResultReq req) {
        logger.info("UriCheckResultServiceImpl|addCheckResult,检测结果管理服务|增加检测结果，参数为：{}", req.toString());
        UriCheckResultEntity uriCheckResultEntity = new UriCheckResultEntity();
        BeanUtils.copyProperties(req, uriCheckResultEntity);
        int res = uriCheckResultMapper.insertSelective(uriCheckResultEntity);
        return res == 1;
    }

    @Override
    public List<UriCheckResultVo> queryCheckResultByUserId(UriUserInfoReq req) {
        logger.info("UriCheckResultServiceImpl|queryCheckResultByUserId,检测结果管理服务|根据userId查询检测结果，参数为：{}", req.toString());
        List<UriCheckResultVo> res = new ArrayList<>();
        List<UriCheckResultEntity> resList = uriCheckResultMapper.selectByUserId(req.getId());
        resList.forEach(entity -> {
            UriCheckResultVo uriCheckResultVo = new UriCheckResultVo();
            BeanUtils.copyProperties(entity, uriCheckResultVo);
            res.add(uriCheckResultVo);
        });
        return res;
    }

    @Override
    public UriCheckResultVo queryStandResult() {
        logger.info("UriCheckResultServiceImpl|queryStandResult,检测结果管理服务|查询标准检测结果");
        UriCheckResultEntity uriCheckResultEntity = uriCheckResultMapper.selectByPrimaryKey(1L);
        UriCheckResultVo uriCheckResultVo = new UriCheckResultVo();
        BeanUtils.copyProperties(uriCheckResultEntity, uriCheckResultVo);
        return uriCheckResultVo;
    }
}
