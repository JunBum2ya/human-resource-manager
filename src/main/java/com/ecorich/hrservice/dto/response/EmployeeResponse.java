package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.EmployeeData;
import lombok.Builder;

@Builder
public record EmployeeResponse(Long memberId, String firstName, String lastName, String email) {
    public static EmployeeResponse from(EmployeeData simpleEmployeeData) {
        if(simpleEmployeeData == null) {
            return null;
        }
        return EmployeeResponse.builder()
                .memberId(simpleEmployeeData.employeeId())
                .firstName(simpleEmployeeData.firstName())
                .lastName(simpleEmployeeData.lastName())
                .email(simpleEmployeeData.email())
                .build();
    }
}
