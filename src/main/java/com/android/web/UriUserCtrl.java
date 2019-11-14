package com.android.web;


import com.android.domain.base.CommonRequest;
import com.android.domain.base.CommonResponse;
import com.android.domain.request.UriAccountInfoReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriAccountInfoVo;
import com.android.domain.response.UriUserInfoVo;
import com.android.service.UriUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * @ClassName UriUserCtrl
 * @Description 账户和用户
 * @Author: lijiao73
 * @Date: 2019/11/14 16:05
 */
@RestController
@RequestMapping("/web/user")
public class UriUserCtrl {
    private static final Logger logger = LoggerFactory.getLogger(UriUserCtrl.class);

    @Resource
    private UriUserService uriUserService;

    @GetMapping("/index")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("index"); // 返回login.html页面
        return model;
    }

    @PostMapping("/addaccount")
    public CommonResponse<Boolean> addAccount(@RequestBody CommonRequest<UriAccountInfoReq> commonRequest) {
        logger.info("UriUserCtrl|addAccount，账户用户控制层|新增账户信息，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        Boolean result = uriUserService.addAccount(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/adduser")
    public CommonResponse<Boolean> addUser(@RequestBody CommonRequest<UriUserInfoReq> commonRequest) {
        logger.info("UriUserCtrl|addUser，账户用户控制层|新增用户信息，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        Boolean result = uriUserService.addRelationUser(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/login")
    public CommonResponse<Boolean> login(@RequestBody CommonRequest<UriAccountInfoReq> commonRequest) {
        logger.info("UriUserCtrl|login，账户用户控制层|账户登录，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        Boolean result = uriUserService.accountLogin(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/selectuser")
    public CommonResponse<UriAccountInfoVo> queryAccountById(@RequestBody CommonRequest<UriAccountInfoReq> commonRequest) {
        logger.info("UriUserCtrl|queryAccountById，账户用户控制层|查询用户信息，入参为：{}", commonRequest.toString());
        CommonResponse<UriAccountInfoVo> res = new CommonResponse<>();
        UriAccountInfoVo result = uriUserService.queryAccountById(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/selectallaccount")
    public CommonResponse<List<UriAccountInfoVo>> queryAllACount(@RequestBody CommonRequest commonRequest) {
        logger.info("UriUserCtrl|queryAllACount，账户用户控制层|查询所有账户信息，入参为：{}", commonRequest.toString());
        CommonResponse<List<UriAccountInfoVo>> res = new CommonResponse<>();
        List<UriAccountInfoVo> result = uriUserService.queryAllACount();
        res.setResultData(result);
        return res;
    }

    @PostMapping("/selectaccountuser")
    public CommonResponse<List<UriUserInfoVo>> queryUserByAccountId(@RequestBody CommonRequest<UriAccountInfoReq> commonRequest) {
        logger.info("UriUserCtrl|queryUserByAccountId，账户用户控制层|根据账户id查询关联用户，入参为：{}", commonRequest.toString());
        CommonResponse<List<UriUserInfoVo>> res = new CommonResponse<>();
        List<UriUserInfoVo> result = uriUserService.queryUserByAccountId(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/selectallaccount")
    public CommonResponse<Boolean> modifyAccount(@RequestBody CommonRequest<UriAccountInfoReq> commonRequest) {
        logger.info("UriUserCtrl|modifyAccount，账户用户控制层|修改账户信息，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        Boolean result = uriUserService.modifyAccount(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }
}
