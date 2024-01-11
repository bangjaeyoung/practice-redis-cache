package org.example.rediscache.exception;

import lombok.Getter;

/**
 * PackageName: org.example.rediscache.exception
 * FileName: BusinessLogicException
 * Author: bangjaeyoung
 * Date: 2024-01-09
 * Description:
 */
@Getter
public class BusinessLogicException extends RuntimeException {
    
    private final ExceptionCode exceptionCode;
    
    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
