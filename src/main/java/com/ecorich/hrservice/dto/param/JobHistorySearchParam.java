package com.ecorich.hrservice.dto.param;

import com.ecorich.hrservice.domain.JobHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Builder
public class JobHistorySearchParam {
    @Getter
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Optional<LocalDate> getStartDate() {
        return Optional.ofNullable(this.startDate);
    }

    public Optional<LocalDate> getEndDate() {
        return Optional.ofNullable(this.endDate);
    }
}
