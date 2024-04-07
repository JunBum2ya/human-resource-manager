package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.JobHistory;
import lombok.Builder;

import java.time.LocalDate;

/**
 * 이력 DTO
 * @param employee
 * @param startDate
 * @param endDate
 * @param job
 * @param department
 */
@Builder
public record JobHistoryDetailData(EmployeeData employee,
                                   LocalDate startDate,
                                   LocalDate endDate,
                                   JobData job,
                                   DepartmentData department) {
    public static JobHistoryDetailData from(JobHistory jobHistory) {
        return JobHistoryDetailData.builder()
                .employee(EmployeeData.from(jobHistory.getEmployee()))
                .startDate(jobHistory.getStartDate())
                .endDate(jobHistory.getEndDate())
                .job(JobData.from(jobHistory.getJob()))
                .department(DepartmentData.from(jobHistory.getDepartment()))
                .build();
    }
}
