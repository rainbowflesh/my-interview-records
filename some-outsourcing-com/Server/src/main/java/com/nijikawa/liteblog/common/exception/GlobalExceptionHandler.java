package com.nijikawa.liteblog.common.exception;
import com.nijikawa.liteblog.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author nijikawa
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 返回状态码
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public static Result handler(ShiroException e) {
        log.error("shiro 运行异常", e);
        return Result.fail(401, e.getMessage(), null);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static Result handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public static Result handler(IllegalArgumentException e) {
        log.error("Assert 断言异常", e);
        return Result.fail(e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public static Result handler(RuntimeException e) {
        log.error("运行异常", e);
        return Result.fail(e.getMessage());
    }
}
