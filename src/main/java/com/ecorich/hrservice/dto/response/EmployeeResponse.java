package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.EmployeeData;
import lombok.Builder;

@Builder
public record EmployeeResponse(Long memberId, String firstName, String lastName, String email) {
    public static EmployeeResponse from(EmployeeData employee) {
        return EmployeeResponse.builder()
                .memberId(employee.employeeId())
                .firstName(employee.firstName())
                .lastName(employee.lastName())
                .email(employee.email())
                .build();
    }
}
