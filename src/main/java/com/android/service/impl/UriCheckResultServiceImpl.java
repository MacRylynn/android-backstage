package com.android.service.impl;

import com.android.common.mapper.UriAccountInfoMapper;
import com.android.common.mapper.UriCheckResultMapper;
import com.android.common.mapper.UriUserInfoMapper;
import com.android.common.mapper.UriUserRelationMapper;
import com.android.service.UriCheckResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UriCheckResultServiceImpl
 * @Description
 * @Author: lijiao73
 * @Date: 2019/11/13 17:13
 */
@Service
public class UriCheckResultServiceImpl implements UriCheckResultService {
    @Resource
    private UriAccountInfoMapper uriAccountInfoMapper;
    @Resource
    private UriCheckResultMapper uriCheckResultMapper;
    @Resource
    private UriUserInfoMapper uriUserInfoMapper;
    @Resource
    private UriUserRelationMapper uriUserRelationMapper;
}
