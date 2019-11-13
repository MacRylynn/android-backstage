package com.android.common.util;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice让Spring认为这是一个统一的异常处理类,表示直接和前端交互
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    //   exceptionHandler 默认的参数HttpServletRequest和Exception
    //   exceptionHandler 处理继承至Exception的所有异常
    private Map<String,Object>exceptionHandler(HttpServletRequest req,Exception e){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("success",false);
        modelMap.put("errMsg",e.getMessage());
        return modelMap;//返回给前台
    }
}
