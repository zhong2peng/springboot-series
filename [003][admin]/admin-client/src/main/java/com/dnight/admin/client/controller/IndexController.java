package com.dnight.admin.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZHONGPENG769
 * @date 2019/10/30
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @GetMapping("t1")
    public String index(){
        return "Spring Boot Client Application";
    }

}
