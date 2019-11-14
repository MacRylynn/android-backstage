package com.android.web;


import com.android.domain.base.CommonRequest;
import com.android.domain.base.CommonResponse;
import com.android.domain.request.UriAccountInfoReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.service.UriUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


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
    public CommonResponse<Boolean> addAccount(CommonRequest<UriAccountInfoReq> commonRequest) {
        logger.info("UriUserCtrl|addAccount，账户用户控制层|新增账户信息，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        Boolean result = uriUserService.addAccount(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/adduser")
    public CommonResponse<Boolean> addUser(CommonRequest<UriUserInfoReq> commonRequest) {
        logger.info("UriUserCtrl|addUser，账户用户控制层|新增用户信息，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        Boolean result = uriUserService.addRelationUser(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/login")
    public CommonResponse<Boolean> login(CommonRequest<UriAccountInfoReq> commonRequest) {
        logger.info("UriUserCtrl|addUser，账户用户控制层|新增用户信息，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        Boolean result = uriUserService.accountLogin(commonRequest.getRequestData());
        res.setResultData(result);
        return res;
    }




}
