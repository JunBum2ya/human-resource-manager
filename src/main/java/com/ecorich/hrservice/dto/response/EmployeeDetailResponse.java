package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.EmployeeDetailData;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record EmployeeDetailResponse(Long employeeId,
                                     String firstName,
                                     String lastName,
                                     String email,
                                     String phoneNumber,
                                     LocalDate hireDate,
                                     JobResponse jobData,
                                     Double salary,
                                     Double commissionPct,
                                     EmployeeResponse manager,
                                     DepartmentResponse department) {
    public static EmployeeDetailResponse from(EmployeeDetailData employeeData) {
        return EmployeeDetailResponse.builder()
                .employeeId(employeeData.employeeId())
                .firstName(employeeData.firstName())
                .lastName(employeeData.lastName())
                .email(employeeData.email())
                .phoneNumber(employeeData.phoneNumber())
                .hireDate(employeeData.hireDate())
                .jobData(JobResponse.from(employeeData.jobData()))
                .salary(employeeData.salary())
                .commissionPct(employeeData.commissionPct())
                .manager(EmployeeResponse.from(employeeData.manager()))
                .department(DepartmentResponse.from(employeeData.department()))
                .build();
    }
}
