package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.domain.constant.ResultStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommonResponse<T>(String code, String message, T data) {
    public static<T> CommonResponse<T> of(ResultStatus status, T data) {
        return new CommonResponse<T>(status.getCode(), status.getMessage(), data);
    }
    public static<T> CommonResponse<T> of(ResultStatus status,String message, T data) {
        return new CommonResponse<T>(status.getCode(), message, data);
    }
    public static CommonResponse of(ResultStatus status) {
        return new CommonResponse(status.getCode(), status.getMessage(), null);
    }
    public static<T> CommonResponse<T> of(T data) {
        return new CommonResponse<T>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), data);
    }
    public static CommonResponse of(String code, String message) {
        return new CommonResponse(code, message, null);
    }
}
