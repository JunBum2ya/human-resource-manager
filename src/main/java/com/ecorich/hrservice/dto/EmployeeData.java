package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Employee;
import lombok.Builder;

import java.time.LocalDate;

/**
 * 직원 DTO
 * @param memberId
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
public record EmployeeData(Long memberId,
                           String firstName,
                           String lastName,
                           String email,
                           String phoneNumber,
                           LocalDate hireDate,
                           JobData jobData,
                           Double salary,
                           Double commissionPct,
                           ManagerData manager,
                           DepartmentData department) {
    public static EmployeeData from(Employee employee) {
        return EmployeeData.builder()
                .memberId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .jobData(JobData.from(employee.getJob()))
                .salary(employee.getSalary())
                .commissionPct(employee.getCommissionPct())
                .manager(ManagerData.from(employee.getManager()))
                .department(DepartmentData.from(employee.getDepartment()))
                .build();
    }
}
