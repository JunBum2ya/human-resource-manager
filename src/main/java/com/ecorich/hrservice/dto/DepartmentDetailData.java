package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Department;
import lombok.Builder;

@Builder
public record DepartmentDetailData(Long departmentId, String departmentName, EmployeeData manager,
                                   LocationDetailData location) {

    public static DepartmentDetailData from(Department department) {
        return DepartmentDetailData.builder()
                .departmentId(department.getId())
                .departmentName(department.getName())
                .manager(department.getManager() != null ? EmployeeData.from(department.getManager()) : null)
                .location(department.getLocation() != null ? LocationDetailData.from(department.getLocation()) : null)
                .build();
    }
}
