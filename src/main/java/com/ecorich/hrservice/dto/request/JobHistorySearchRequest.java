package com.ecorich.hrservice.dto.request;

import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class JobHistorySearchRequest {
    private String startDate;
    private String endDate;
    public JobHistorySearchParam toJobHistorySearchParam(Long employeeId) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return JobHistorySearchParam.builder()
                .employeeId(employeeId)
                .startDate(StringUtils.hasText(startDate) ? LocalDate.parse(startDate,dateTimeFormatter) : null)
                .endDate(StringUtils.hasText(endDate) ? LocalDate.parse(endDate,dateTimeFormatter) : null)
                .build();
    }
}
