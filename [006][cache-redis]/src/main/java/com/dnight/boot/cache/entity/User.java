package com.dnight.boot.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {


    private static final long serialVersionUID = 8478681754655377786L;
    private Long id;
    private String name;


}
