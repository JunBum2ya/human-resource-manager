package com.ecorich.hrservice.dto.request;

import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentSearchRequest {
    private Long departmentId;
    private String departmentName;
    private Long locationId;

    public DepartmentSearchParam toDepartmentSearchParam() {
        return DepartmentSearchParam.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .locationId(locationId)
                .build();
    }
}
