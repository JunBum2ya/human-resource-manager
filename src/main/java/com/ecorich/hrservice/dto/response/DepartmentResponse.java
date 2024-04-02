package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.DepartmentData;
import lombok.Builder;

@Builder
public record DepartmentResponse(Long departmentId, String departmentName) {
    public static DepartmentResponse from(DepartmentData departmentData) {
        if(departmentData == null) {
            return null;
        }
        return DepartmentResponse.builder()
                .departmentId(departmentData.departmentId())
                .departmentName(departmentData.departmentName())
                .build();
    }
}
