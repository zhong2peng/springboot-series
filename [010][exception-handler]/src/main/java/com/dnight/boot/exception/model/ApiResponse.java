package com.dnight.boot.exception.model;

import com.dnight.boot.exception.constant.Status;
import com.dnight.boot.exception.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Integer code;
    private String message;
    private Object data;

    public static ApiResponse of(Integer code, String message, Object data){
        return new ApiResponse(code, message, data);
    }

    public static ApiResponse ofSuccess(Object data){
        return ofStatus(Status.OK, data);
    }

    public static ApiResponse ofMessage(String message){
        return of(Status.OK.getCode(), message, null);
    }

    public static ApiResponse ofStatus(Status status){
        return ofStatus(status, null);
    }

    public static ApiResponse ofStatus(Status status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    public static <T extends BaseException> ApiResponse ofException(T t, Object data){
        return of(t.getCode(), t.getMessage(), data);
    }

    public static <T extends BaseException> ApiResponse ofException(T t){
        return ofException(t, null);
    }

}
