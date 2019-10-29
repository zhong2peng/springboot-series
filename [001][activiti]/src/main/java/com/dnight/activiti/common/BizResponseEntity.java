package com.dnight.activiti.common;

import lombok.Data;

/**
 * @author ZHONGPENG769
 * @date 2019/10/29
 */
@Data
public class BizResponseEntity {

    private String code;

    private String message = "";

    private Object data;

    private String redirect = "";

    public BizResponseEntity() {}

    public BizResponseEntity(Object data ) {
        this.data = data;
    }

    public BizResponseEntity(String code, String message ) {
        this.code = code;
        this.message = message;
    }

    public static BizResponseEntity ok(Object data ) {
        return new BizResponseEntity( data );
    }

    public static BizResponseEntity ok() {
        return new BizResponseEntity();
    }

    public static BizResponseEntity error(String code, String message ) {
        return new BizResponseEntity( code, message );
    }
}
