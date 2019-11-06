package com.dnight.boot.exception.handler;

import com.dnight.boot.exception.exception.JsonException;
import com.dnight.boot.exception.exception.PageException;
import com.dnight.boot.exception.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse jsonErrorHandler(JsonException jsonException){
        log.error("[JsonException]: {}", jsonException.getMessage());
        return ApiResponse.ofException(jsonException);
    }

    @ExceptionHandler(value = PageException.class)
    public ModelAndView pageErrorHandler(PageException pageException){
        log.error("[PageException]: {}", pageException.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", pageException.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }
}
