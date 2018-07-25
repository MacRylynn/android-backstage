package com.lee.android.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.android.entity.UserInfo;
import com.lee.android.entity.UserTestResult;
import com.lee.android.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController      //@RestController是ResponseBody和 Controller两个的组合，表示UserInfoController返回的是html里面的body值
@RequestMapping("/web")  //指定根路由（位置）
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/index")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("index"); // 返回login.html页面
        return model;
    }

    //通过启动Tomcat来访问其中的一个方法
    //返回所有的用户信息
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    //返回的是一个map对象(键值对)key是String value是Object
    public ModelAndView ListUserInfo(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
        ModelAndView model = new ModelAndView("show_users");
        List<UserInfo> List = userInfoService.getUserInfoList();//获取所有用户信息列表
        PageHelper.startPage(pn,800);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(List);
        model.addObject("pageInfo",pageInfo);
        return model;
    }
    @RequestMapping(value = "/getoneuser", method = RequestMethod.GET)
    private Map<String, Object> getUserInfoById(Integer userId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        UserInfo userInfo = userInfoService.getUserInfoById(userId);//通过ID来获取用户信息
        modelMap.put("UserInfo", userInfo);
        return modelMap;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    //参数接收UserInfo的实体类，但是前端不可能传递UserInfo类，@RequestBody表示传入json对象
    //接收前台传递进来的 userName userPassword logTimes 然后给定类实现注册功能
    public boolean addUserInfo(String userName, String userPassword) {
        //得到所有的用户信息，用来判断是用户是否存在
        boolean a = true;
        List<UserInfo> List = userInfoService.getUserInfoList();
        //将传进来的参数赋给用户类
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        //和数据库里面的信息比较
        for (int i = 0; i < List.size(); i++) {
            UserInfo userInfo1 = List.get(i);
            if (userInfo1.getUserName().equals(userName)) {
                a = false;
                break;
            }
        }
        if (a) {
            a = userInfoService.addUserInfo(userInfo);
        }
        return a;
    }

    @RequestMapping(value = "/modifyuser", method = RequestMethod.POST, consumes = "application/json")
    private Map<String, Object> modifyUserInfo(@RequestBody UserInfo userInfo) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", userInfoService.modifyUserInfo(userInfo));//修改用户信息
        return modelMap;
    }


    @RequestMapping(value = "/removeuser", method = RequestMethod.GET)
    private Map<String, Object> removeUserInfo(Integer userId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", userInfoService.deleteUserInfo(userId));//删除用户信息
        return modelMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private boolean login(String userName, String userPassword) {
        List<UserInfo> List = userInfoService.getUserInfoList();
        boolean signState = false;
        for (int i = 0; i < List.size(); i++) {
            UserInfo userInfo1 = List.get(i);

            if (userInfo1.getUserName().equals(userName) && userInfo1.getUserPassword().equals(userPassword)) {
                signState =true;
                System.out.println("登录成功");
                break;
            } else {
                signState = false;
            }
        }
        return signState;
    }
}
