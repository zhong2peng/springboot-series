package com.dnight.cache.ehcache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZHONGPENG769
 * @date 2019/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -8997256507895626778L;

    private Long id;

    private String name;
}
