package com.ecorich.hrservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UpdateDepartmentSalaryRequest {
    @NotNull(message = "인상 비율을 입력하세요.")
    private Double rate;
}
