package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Employee;
import lombok.Builder;

/**
 * 관리자 정보 DTO
 * @param memberId
 * @param firstName
 * @param lastName
 * @param email
 */
@Builder
public record ManagerData(Long memberId, String firstName, String lastName, String email) {
    public static ManagerData from(Employee employee) {
        if(employee == null) {
            return null;
        }
        return ManagerData.builder()
                .memberId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
