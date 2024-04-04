package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Employee;
import lombok.Builder;

/**
 * 관리자 정보 DTO
 * @param employeeId
 * @param firstName
 * @param lastName
 * @param email
 */
@Builder
public record SimpleEmployeeData(Long employeeId, String firstName, String lastName, String email) {
    public static SimpleEmployeeData from(Employee employee) {
        if(employee == null) {
            return null;
        }
        return SimpleEmployeeData.builder()
                .employeeId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
