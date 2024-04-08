package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.EmployeeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

/**
 * 직원 응답 포맷
 * @param employeeId
 * @param firstName
 * @param lastName
 * @param email
 * @param phoneNumber
 * @param hireDate
 * @param salary
 * @param commissionPct
 */
@Schema(name = "직원 응답 포맷", description = "직원 관련 정보의 응답 포맷")
@Builder
public record EmployeeResponse(Long employeeId, String firstName, String lastName, String email, String phoneNumber,
                               LocalDate hireDate, Double salary, Double commissionPct) {
    public static EmployeeResponse from(EmployeeData employee) {
        return EmployeeResponse.builder()
                .employeeId(employee.employeeId())
                .firstName(employee.firstName())
                .lastName(employee.lastName())
                .email(employee.email())
                .phoneNumber(employee.phoneNumber())
                .hireDate(employee.hireDate())
                .salary(employee.salary())
                .commissionPct(employee.commissionPct())
                .build();
    }
}
