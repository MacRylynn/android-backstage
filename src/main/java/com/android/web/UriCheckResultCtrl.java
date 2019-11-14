package com.android.web;

import com.android.service.UriCheckResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @ClassName UriCheckResultCtrl
 * @Description 检测结果
 * @Author: lijiao73
 * @Date: 2019/11/14 16:07
 */
@RestController
@RequestMapping("/web/check")
public class UriCheckResultCtrl {
    private static final Logger logger = LoggerFactory.getLogger(UriCheckResultCtrl.class);

    @Resource
    private UriCheckResultService uriCheckResultService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login"); // 返回login.html页面
        return model;
    }
}
