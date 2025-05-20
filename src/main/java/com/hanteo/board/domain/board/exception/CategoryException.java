package com.hanteo.board.domain.board.exception;

import com.hanteo.board.domain.exception.BusinessException;

public class CategoryException extends BusinessException {
    private final int statusCode;
    private final String error;

    private CategoryException(int statusCode, String error, String message) {
        super(message);
        this.statusCode = statusCode;
        this.error = error;
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public String errorCode() {
        return error;
    }

    public static CategoryException notFound() {
        return new CategoryException(404, "Category Not Found", "카테고리를 찾을 수 없습니다.");
    }
}
