package com.dnight.log.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ZHONGPENG769
 * @date 2019/11/8
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return UUID.randomUUID().toString();
    }
}
