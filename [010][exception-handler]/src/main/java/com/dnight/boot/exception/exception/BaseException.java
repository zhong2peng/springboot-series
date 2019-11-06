package com.dnight.boot.exception.exception;

import com.dnight.boot.exception.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
