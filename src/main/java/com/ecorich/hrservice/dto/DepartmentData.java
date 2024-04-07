package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Department;
import lombok.Builder;

/**
 * 부서 DTO
 * @param departmentId
 * @param departmentName
 */
@Builder
public record DepartmentData(Long departmentId, String departmentName) {
    public static DepartmentData from(Department department) {
        return DepartmentData.builder()
                .departmentId(department.getId())
                .departmentName(department.getName())
                .build();
    }
}
