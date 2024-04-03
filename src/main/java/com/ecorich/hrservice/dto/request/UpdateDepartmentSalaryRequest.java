package com.ecorich.hrservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDepartmentSalaryRequest {
    @NotNull(message = "인상 비율을 입력하세요.")
    private Double rate;
}
