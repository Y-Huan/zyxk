package com.zyy.zyxk.common.exception;

import com.zyy.zyxk.common.constant.ErrorCode;

/**
  *
  * @desc 业务处理异常
  * @author Yang.H
  * @date 2021/8/17
  *
  **/
public class BizException extends BaseException {
    private ErrorCode errorCode;

    private static final long serialVersionUID = 1L;

    public BizException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
