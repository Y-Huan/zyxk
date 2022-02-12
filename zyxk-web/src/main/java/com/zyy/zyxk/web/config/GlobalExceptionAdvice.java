package com.zyy.zyxk.web.config;

import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.exception.AuthenticationException;
import com.zyy.zyxk.common.exception.BizException;
import com.zyy.zyxk.common.exception.JwtException;
import com.zyy.zyxk.common.vo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @Description controller全剧异常处理类
 * @Author Yang.H
 * @Date 2021/6/2
 *
 **/
@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<Boolean> bindExceptionHandler(BindException exception) {
        exception.printStackTrace();
        BindingResult result = exception.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        if (result.hasErrors()) {
            Set<FieldError> fieldErrors = new HashSet<>(result.getFieldErrors());
            errorMsg.append("请求入参校验失败：");
            for(FieldError fieldError:fieldErrors){
                errorMsg.append(fieldError.getDefaultMessage()+" | ");
            }
        }
        return Response.fail(ErrorCode.BIND_ERROR,errorMsg.toString());
    }

    /**
     * 处理Hibernate Validator校验URL参数抛出异常
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<Boolean> handlerConstraintViolationException(ConstraintViolationException e) {
        // 1.校验
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return Response.success();
        }

        // 2.错误信息
        ConstraintViolation<?> constraintViolation = constraintViolations.iterator().next();
        String name = constraintViolation.getPropertyPath().toString();
        String message = constraintViolation.getMessage();
        String errMsg = "参数" + name + "：" + message;

        // 3.return
        return Response.fail(ErrorCode.CONSTRAINT_ERROR,errMsg);
    }

    @ResponseBody
    @ExceptionHandler(JwtException.class)
    public Object handleJwtException(JwtException e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "jwt认证失败";
        }

        return Response.fail(ErrorCode.JWT_ERROR,message);
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthenticationException(AuthenticationException e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "认证失败";
        }

        return Response.fail(ErrorCode.USER_EXISTED,message);
    }

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public Object handleBizException(BizException e) {
        return Response.fail(e.getErrorCode());
    }

}