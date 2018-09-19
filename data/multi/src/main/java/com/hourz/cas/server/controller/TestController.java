package com.hourz.cas.server.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hourz.cas.server.service.TestService;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping("/test1")
    public String test(){
//     for(Demo d:testService.getList()){
//         System.out.println(d);
//     }
        for(Demo demo : testService.getListByDs1()){
            System.out.println(demo);
        }
        return"ok";
    }

}
