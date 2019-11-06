package com.dnight.boot.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Getter
@AllArgsConstructor
public enum Status {

    OK(200, "操作成功"),
    UNKNOWN_ERROR(500, "服务器出错啦");

    private Integer code;
    private String message;

}
