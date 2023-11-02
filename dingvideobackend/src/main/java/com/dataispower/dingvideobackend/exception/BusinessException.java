package com.dataispower.dingvideobackend.exception;

/**
 * author:heroding
 * date:2023/11/1 15:39
 * description：
 **/
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -5906894231057426024L;

    private String errorMsg;

    private String errorCode;

    private ErrorType errorType;

    public BusinessException(String message, String code) {
        super(message + "#" + code);
        this.errorMsg = message;
        this.errorCode = code;
    }

    public BusinessException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public BusinessException(ErrorType errorType) {
        this.errorType = errorType;
        this.errorCode = errorType.getCode();
        this.errorMsg = errorType.getMsg();
    }

}

