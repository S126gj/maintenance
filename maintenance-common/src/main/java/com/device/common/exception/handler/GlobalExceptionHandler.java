package com.device.common.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.device.common.exception.ApiError;
import com.device.common.exception.BIZException;
import com.device.common.exception.BadRequestException;
import com.device.common.response.CommonCode;
import com.device.common.utils.R;
import com.device.common.utils.ThrowableUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //拦截所有
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(e.hashCode(),e.getMessage());
    }
    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    public R error(ArithmeticException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(e.hashCode(),e.getMessage());
    }

    //自定义异常 通用
    @ExceptionHandler(BIZException.class)
    public R error(BIZException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(e.getCode(), e.getMsg());
    }

    //自定义异常
    @ExceptionHandler(value = BadRequestException.class)
    public R badRequestException(BadRequestException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(ApiError.error(e.getStatus(),e.getMessage()));
    }
    //数据异常
    @ExceptionHandler(value=DataAccessException.class)
    public R dataAccessErrorHandler(DataAccessException e){
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(e.hashCode(), e.getMessage());
    }

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    public R BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(message);
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public R ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(message);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(message);
    }

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(NotLoginException.class)
    public R handlerNotLoginException(NotLoginException nle) {
        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未能读取到有效 token";
        }
        else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token 无效";
        }
        else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token 已过期";
        }
        else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token 已被顶下线";
        }
        else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token 已被踢下线";
        }
        else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return R.error(CommonCode.TOKEN_VAILD_ERROR.code(), message);
    }
}
