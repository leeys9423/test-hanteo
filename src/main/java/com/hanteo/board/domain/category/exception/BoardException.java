package com.hanteo.board.domain.category.exception;

import com.hanteo.board.domain.exception.BusinessException;

public class BoardException extends BusinessException {
    private final int statusCode;
    private final String error;

    private BoardException(int statusCode, String error, String message) {
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

    public static BoardException notFound() {
        return new BoardException(404, "Board Not Found", "게시판을 찾을 수 없습니다.");
    }
}
