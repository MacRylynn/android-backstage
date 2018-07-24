package com.lee.android;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @RequestMapping(value = "/hellsado",method = RequestMethod.GET)
    public String hello(){
        return "Hello Spring";
    }
}
