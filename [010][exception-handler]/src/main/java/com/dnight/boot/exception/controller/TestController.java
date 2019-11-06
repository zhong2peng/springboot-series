package com.dnight.boot.exception.controller;

import com.dnight.boot.exception.constant.Status;
import com.dnight.boot.exception.exception.JsonException;
import com.dnight.boot.exception.exception.PageException;
import com.dnight.boot.exception.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@RestController
public class TestController {

    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException(){
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("/page")
    public ApiResponse pageException(){
        throw new PageException(Status.UNKNOWN_ERROR);
    }
}
