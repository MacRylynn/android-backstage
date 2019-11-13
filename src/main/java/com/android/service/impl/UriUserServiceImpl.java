package com.android.service.impl;

import com.android.common.mapper.UriAccountInfoMapper;
import com.android.common.mapper.UriCheckResultMapper;
import com.android.common.mapper.UriUserInfoMapper;
import com.android.common.mapper.UriUserRelationMapper;
import com.android.service.UriUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UriUserServiceImpl
 * @Description
 * @Author: lijiao73
 * @Date: 2019/11/13 17:13
 */
@Service
public class UriUserServiceImpl implements UriUserService {
    @Resource
    private UriAccountInfoMapper uriAccountInfoMapper;
    @Resource
    private UriCheckResultMapper uriCheckResultMapper;
    @Resource
    private UriUserInfoMapper uriUserInfoMapper;
    @Resource
    private UriUserRelationMapper uriUserRelationMapper;

}
