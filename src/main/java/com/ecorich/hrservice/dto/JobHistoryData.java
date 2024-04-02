package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.JobHistory;
import lombok.Builder;

import java.time.LocalDate;

/**
 * 이력 DTO
 * @param employeeData
 * @param startDate
 * @param endDate
 * @param jobData
 * @param departmentData
 */
@Builder
public record JobHistoryData(SimpleEmployeeData employeeData,
                             LocalDate startDate,
                             LocalDate endDate,
                             JobData jobData,
                             DepartmentData departmentData) {
    public static JobHistoryData from(JobHistory jobHistory) {
        return JobHistoryData.builder()
                .employeeData(SimpleEmployeeData.from(jobHistory.getEmployee()))
                .startDate(jobHistory.getStartDate())
                .endDate(jobHistory.getEndDate())
                .jobData(JobData.from(jobHistory.getJob()))
                .departmentData(DepartmentData.from(jobHistory.getDepartment()))
                .build();
    }
}
