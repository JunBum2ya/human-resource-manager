package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.JobHistoryDetailData;
import lombok.Builder;

import java.time.LocalDate;

/**
 * 이력 응답 포맷
 * @param employee
 * @param startDate
 * @param endDate
 * @param job
 * @param department
 */
@Builder
public record JobHistoryDetailResponse(EmployeeResponse employee, LocalDate startDate, LocalDate endDate,
                                       JobResponse job, DepartmentResponse department) {
    
    public static JobHistoryDetailResponse from(JobHistoryDetailData jobHistory) {
        return JobHistoryDetailResponse.builder()
                .employee(EmployeeResponse.from(jobHistory.employee()))
                .startDate(jobHistory.startDate())
                .endDate(jobHistory.endDate())
                .job(JobResponse.from(jobHistory.job()))
                .department(DepartmentResponse.from(jobHistory.department()))
                .build();
    } 
}
