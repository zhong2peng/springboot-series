package com.dnight.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dnight.dubbo.common.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Service
@Slf4j
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        log.info("someone is calling me ......");
        return "say hello to: " + name;
    }
}
