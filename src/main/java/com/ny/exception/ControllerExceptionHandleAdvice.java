package com.ny.exception;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ny.vo.Response;

/**
 * Controller 异常统一处理
 * 
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandleAdvice {
    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandleAdvice.class);

    @ExceptionHandler(ParseException.class)
    public Response handleParseException(ParseException e) {
        logger.error(e.getMessage());
        return new Response().failure("时间格式化错误！");
    }

    @ExceptionHandler(IOException.class)
    public Response handleIOException(IOException e) {
        logger.error(e.getMessage());
        return new Response().failure("I/O异常！");
    }
}
