package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.EmployeeData;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record EmployeeResponse(Long memberId,
                               String firstName,
                               String lastName,
                               String email,
                               String phoneNumber,
                               LocalDate hireDate,
                               JobResponse jobData,
                               Double salary,
                               Double commissionPct,
                               ManagerResponse manager,
                               DepartmentResponse department) {
    public static EmployeeResponse from(EmployeeData employeeData) {
        return EmployeeResponse.builder()
                .memberId(employeeData.memberId())
                .firstName(employeeData.firstName())
                .lastName(employeeData.lastName())
                .email(employeeData.email())
                .phoneNumber(employeeData.phoneNumber())
                .hireDate(employeeData.hireDate())
                .jobData(JobResponse.from(employeeData.jobData()))
                .salary(employeeData.salary())
                .commissionPct(employeeData.commissionPct())
                .manager(ManagerResponse.from(employeeData.manager()))
                .department(DepartmentResponse.from(employeeData.department()))
                .build();
    }
}
