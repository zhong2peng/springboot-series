package com.dnight.boot.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ZHONGPENG769
 * @date 2019/11/7
 */
@RestController
@Slf4j
public class IndexController {

    @GetMapping("/index")
    public String index(){
        log.info("index "+System.currentTimeMillis());
        return UUID.randomUUID().toString();
    }
}
