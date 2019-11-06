package com.dnight.boot.cache.service;

import com.dnight.boot.cache.entity.User;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
public interface UserService {

    User saveOrUpdate(User user);

    User get(Long id);

    void delete(Long id);
}
