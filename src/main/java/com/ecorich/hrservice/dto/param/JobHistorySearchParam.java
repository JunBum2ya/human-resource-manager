package com.ecorich.hrservice.dto.param;

import com.ecorich.hrservice.domain.JobHistory;
import lombok.Builder;

import java.util.List;

@Builder
public record JobHistorySearchParam(Long employeeId) {
}
