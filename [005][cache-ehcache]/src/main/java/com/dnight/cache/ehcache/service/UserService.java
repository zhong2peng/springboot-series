package com.dnight.cache.ehcache.service;

import com.dnight.cache.ehcache.entity.User;

/**
 * @author ZHONGPENG769
 * @date 2019/10/31
 */
public interface UserService {

    User saveOrUpdate(User user);

    User get(Long id);

    void delete(Long id);
}
