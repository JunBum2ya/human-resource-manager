package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Employee;
import lombok.Builder;

import java.time.LocalDate;

/**
 * 관리자 정보 DTO
 *
 * @param employeeId
 * @param firstName
 * @param lastName
 * @param email
 */
@Builder
public record EmployeeData(Long employeeId, String firstName, String lastName, String email, String phoneNumber,
                           LocalDate hireDate, Double salary, Double commissionPct) {
    public static EmployeeData from(Employee employee) {
        return EmployeeData.builder()
                .employeeId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary())
                .commissionPct(employee.getCommissionPct())
                .build();
    }
}
