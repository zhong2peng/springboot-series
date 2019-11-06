package com.dnight.boot.exception.exception;

import com.dnight.boot.exception.constant.Status;
import lombok.Getter;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Getter
public class JsonException extends BaseException {
    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
