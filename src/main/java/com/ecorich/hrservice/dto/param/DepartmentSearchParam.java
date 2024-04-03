package com.ecorich.hrservice.dto.param;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Optional;

@AllArgsConstructor
@Builder
public class DepartmentSearchParam {
    private Long departmentId;
    private String departmentName;
    private Long locationId;

    public Optional<Long> getDepartmentId() {
        return Optional.ofNullable(departmentId);
    }

    public Optional<String> getDepartmentName() {
        return Optional.ofNullable(departmentName);
    }

    public Optional<Long> getLocationId() {
        return Optional.ofNullable(locationId);
    }
}
