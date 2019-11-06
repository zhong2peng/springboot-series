package com.dnight.boot.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }
}
