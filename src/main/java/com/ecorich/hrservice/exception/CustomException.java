package com.ecorich.hrservice.exception;

import com.ecorich.hrservice.domain.constant.ResultStatus;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String code;
    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ResultStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
    }
}
