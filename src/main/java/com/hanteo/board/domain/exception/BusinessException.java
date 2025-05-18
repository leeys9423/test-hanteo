package com.hanteo.board.domain.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public abstract int statusCode();
    public abstract String errorCode();
}
