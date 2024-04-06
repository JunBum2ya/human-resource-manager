package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Employee;
import lombok.Builder;

import java.time.LocalDate;

/**
 * 직원 DTO
 *
 * @param employeeId
 * @param firstName
 * @param lastName
 * @param email
 * @param phoneNumber
 * @param hireDate
 * @param jobData
 * @param salary
 * @param commissionPct
 * @param manager
 * @param department
 */
@Builder
public record EmployeeDetailData(Long employeeId, String firstName, String lastName, String email, String phoneNumber,
                                 LocalDate hireDate, JobData jobData, Double salary, Double commissionPct,
                                 EmployeeData manager, DepartmentData department) {
    public static EmployeeDetailData from(Employee employee) {
        return EmployeeDetailData.builder()
                .employeeId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .jobData(JobData.from(employee.getJob()))
                .salary(employee.getSalary())
                .commissionPct(employee.getCommissionPct())
                .manager(employee.getManager() != null ? EmployeeData.from(employee.getManager()) : null)
                .department(DepartmentData.from(employee.getDepartment()))
                .build();
    }
}
