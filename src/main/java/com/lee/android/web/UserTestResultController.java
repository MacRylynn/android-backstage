package com.lee.android.web;



import com.lee.android.entity.UserTestResult;
import com.lee.android.service.UserTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController      //@RestController是ResponseBody和 Controller两个的组合，表示UserInfoController返回的是html里面的body值
@RequestMapping("/web")  //指定根路由（位置）
public class UserTestResultController {

    @Autowired
    private UserTestResultService userTestResultService;
    @GetMapping("/result")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("results"); // 返回login.html页面
        return model;
    }
//通过启动Tomcat来访问其中的一个方法
    //返回所有的用户信息
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    //返回的是一个map对象(键值对)key是String value是Object
    private Map<String, Object> ListUserTestResult() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<UserTestResult> List = userTestResultService.getUserTestResultList();//获取所有用户信息列表
        modelMap.put("UserTestResultList", List);
        return modelMap;
    }
    @RequestMapping(value = "/getoneresult", method = RequestMethod.GET)
    private Map<String, Object> getUserTestResultById(Integer testResultId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        UserTestResult userTestResult = userTestResultService.getUserTestResultById(testResultId);//通过ID来获取用户信息
        modelMap.put("UserTestResult", userTestResult);
        return modelMap;
    }

    @RequestMapping(value = "/addresult", method = RequestMethod.POST)
    public boolean addUserInfo(String testResultUser, String testResult,String testImage) {
        UserTestResult userTestResult = new UserTestResult();
        userTestResult.setTestResultUser(testResultUser);
        userTestResult.setTestResult(testResult);
        userTestResult.setTestImage(testImage);
        boolean effectBoolean = userTestResultService.addUserTestResult(userTestResult);
        return effectBoolean;
    }

    @RequestMapping(value = "/removeresults", method = RequestMethod.GET)
    private Map<String, Object> removeUserTestResult(Integer testResultId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", userTestResultService.deleteUserTestResult(testResultId));//删除用户信息
        return modelMap;
    }
}
