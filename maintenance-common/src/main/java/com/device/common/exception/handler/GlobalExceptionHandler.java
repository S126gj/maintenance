package com.device.common.exception.handler;

import com.device.common.exception.ApiError;
import com.device.common.exception.BIZException;
import com.device.common.exception.BadRequestException;
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

    // // 捕获字段过长异常 MysqlDataTruncation：Data truncation: Data too long for column
    // @ExceptionHandler(DataIntegrityViolationException.class)
    // public R MysqlDataTruncationHandler(DataIntegrityViolationException e) {
    //     boolean dataTooLong = e.getMessage().contains("Data too long");
    //     if (dataTooLong) {
    //         Pattern pattern = Pattern.compile("\'(.*?)\'");
    //         Matcher m = pattern.matcher(e.getMessage());
    //         String message = "";
    //         while (m.find()) {
    //             message = m.group().trim().replace("'", "");
    //         }
    //         log.error(ThrowableUtil.getStackTrace(e));
    //         return R.error(/**message + */"字段过长");
    //     }
    //     return R.ok();
    // }
}
