package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.domain.constant.ResultStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "공통 응답 포맷", description = "성공 여부를 보여주는 공통 응답 포맷")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommonResponse<T>(
        @Schema(name = "code", description = "응답코드") String code,
        @Schema(name = "message", description = "응답 메시지") String message,
        @Schema(name = "data", description = "데이터") T data
) {
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
