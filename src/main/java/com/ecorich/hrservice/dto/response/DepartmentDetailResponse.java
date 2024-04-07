package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.DepartmentDetailData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "부서 상세 응답 포맷", description = "부서 상세 응답 데이터 양식")
@Builder
public record DepartmentDetailResponse(Long departmentId, String departmentName, EmployeeResponse manager,
                                       LocationResponse location) {

    public static DepartmentDetailResponse from(DepartmentDetailData department) {
        return DepartmentDetailResponse.builder()
                .departmentId(department.departmentId())
                .departmentName(department.departmentName())
                .manager(EmployeeResponse.from(department.manager()))
                .location(LocationResponse.from(department.location()))
                .build();
    }
}
