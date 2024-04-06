package com.ecorich.hrservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(name = "임금 인상 요청 포맷", description = "임금 인상을 위한 요청 포맷")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UpdateDepartmentSalaryRequest {
    @Schema(name = "rete", description = "임금 인상 비율", example = "50.0")
    @NotNull(message = "인상 비율을 입력하세요.")
    private Double rate;
}
