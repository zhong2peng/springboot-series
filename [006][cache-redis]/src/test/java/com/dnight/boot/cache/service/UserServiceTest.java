package com.dnight.boot.cache.service;

import com.dnight.boot.cache.UTApplication;
import com.dnight.boot.cache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Slf4j
public class UserServiceTest extends UTApplication {

    @Autowired
    private UserService userService;

    @Test
    public void getTwice(){
        User user1 = userService.get(1L);
        log.debug("[user1] = {}", user1);

        User user2 = userService.get(1L);
        log.debug("[user2] = {}", user2);
        // 查看日志，只打印一次日志，证明缓存生效
    }

    @Test
    public void getAfterSave(){
        userService.saveOrUpdate(new User(4L, "Dnight"));
        User user = userService.get(4L);
        log.debug("[user] = {}", user);
        // 查看日志，只打印保存用户的日志，查询是未触发查询日志，因此缓存生效
    }

    @Test
    public void deleteUser(){
        userService.get(1L);
        userService.delete(1L);
        userService.get(1L);
    }
}
