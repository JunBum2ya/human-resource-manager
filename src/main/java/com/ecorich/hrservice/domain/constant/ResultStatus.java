package com.ecorich.hrservice.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultStatus {
    SUCCESS("200", "success"),
    UNAUTHENTICATED_USER("001", "허용되지 않은 사용자입니다."),
    ACCESS_NOT_EXIST_ENTITY("002", "존재 하지 않는 엔티티에 접근했습니다."),
    NOT_VALID_REQUEST("003", "필수 파라미터가 입력되지 않았습니다."),
    DUPLICATE_UNIQUE_PROPERTY("004", "중복된 값이 존재합니다."),
    USE_NOT_PERSIST_ENTITY("005", "DB에 저장되지 않은 객체입니다."),
    NOT_SATISFY_PARAMETER_FORMAT("006", "입력 데이터를 다시한번 확인해 주십시오."),
    UNKNOWN_EXCEPTION("100", "알 수 없는 오류입니다.");

    private final String code;
    private final String message;
}
