package com.android.service.impl;

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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private UriAccountInfoMapper uriAccountInfoMapper;

    @Resource
    private UriCheckResultMapper uriCheckResultMapper;

    @Resource
    private UriUserInfoMapper uriUserInfoMapper;

    @Resource
    private UriUserRelationMapper uriUserRelationMapper;

    @Override
    public Boolean addCheckResult(UriCheckResultReq req) {
        logger.info("UriCheckResultServiceImpl|addCheckResult,检测结果管理服务|增加检测结果，参数为：{}", req.toString());
        return Boolean.FALSE;
    }

    @Override
    public List<UriCheckResultVo> queryCheckResultByUserId(UriUserInfoReq req) {
        logger.info("UriCheckResultServiceImpl|queryCheckResultByUserId,检测结果管理服务|根据userID查询检测结果，参数为：{}", req.toString());
        return null;
    }

    @Override
    public UriCheckResultVo queryStandResult() {
        logger.info("UriCheckResultServiceImpl|queryStandResult,检测结果管理服务|查询标准检测结果");
        return null;
    }
}
