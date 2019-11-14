package com.android.service;

import com.android.domain.request.UriAccountInfoReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriAccountInfoVo;
import com.android.domain.response.UriUserInfoVo;

import java.util.List;

/**
 * @ClassName UriUserService
 * @Description 用户管理服务
 * @Author: lijiao73
 * @Date: 2019/11/13 17:12
 */
public interface UriUserService {

    /**
     * 功能描述: <新增账号（不等于新增用户，因为一个账号下可以挂很多用户）>
     *
     * @Param: [req]
     * @Return: boolean
     * @Author: lijiao73
     * @Date: 2019/11/14 10:05
     */
    Boolean addAccount(UriAccountInfoReq req);

    /**
     * 功能描述: <新增关联用户信息>
     *
     * @Param: [req]
     * @Return: java.lang.Boolean
     * @Author: lijiao73
     * @Date: 2019/11/14 15:27
     */
    Boolean addRelationUser(UriUserInfoReq req);

    /**
     * 功能描述: <用户登录服务>
     *
     * @Param: [req]
     * @Return: boolean
     * @Author: lijiao73
     * @Date: 2019/11/14 10:07
     */
    Boolean accountLogin(UriAccountInfoReq req);

    /**
     * 功能描述: <根据Id来获取账号信息>
     *
     * @Param: [req]
     * @Return: <com.android.domain.response.UriAccountInfoVo>
     * @Author: lijiao73
     * @Date: 2019/11/14 10:11
     */
    UriAccountInfoVo queryAccountById(UriAccountInfoReq req);

    /**
     * 功能描述: <获取所有账户>
     *
     * @Param: []
     * @Return: java.util.List<com.android.domain.response.UriAccountInfoVo>
     * @Author: lijiao73
     * @Date: 2019/11/14 10:12
     */
    List<UriAccountInfoVo> queryAllACount();

    /**
     * 功能描述: <根据账户获取用户树>
     *
     * @Param: [req]
     * @Return: java.util.List<com.android.domain.response.UriUserInfoVo>
     * @Author: lijiao73
     * @Date: 2019/11/14 10:10
     */
    List<UriUserInfoVo> queryUserByAccountId(UriAccountInfoReq req);

    /**
     * 功能描述: <修改账户信息>
     *
     * @Param: [req]
     * @Return: java.lang.Boolean
     * @Author: lijiao73
     * @Date: 2019/11/14 10:40
     */
    Boolean modifyAccount(UriAccountInfoReq req);

}
