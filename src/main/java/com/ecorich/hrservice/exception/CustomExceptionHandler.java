package com.ecorich.hrservice.exception;

import com.ecorich.hrservice.domain.constant.ResultStatus;
import com.ecorich.hrservice.dto.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * CustomException 핸들링
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse> handleCustomException(CustomException e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(CommonResponse.of(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        List<String> messages = e.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(CommonResponse.of(ResultStatus.NOT_VALID_REQUEST, messages.getFirst(), messages));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        log.warn(e.getMessage());
        return ResponseEntity.internalServerError().body(CommonResponse.of(ResultStatus.NOT_SATISFY_PARAMETER_FORMAT));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(CommonResponse.of(ResultStatus.UNKNOWN_EXCEPTION));
    }

}
