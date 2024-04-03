package com.ecorich.hrservice.repository.querydsl;

import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentRepositoryCustom {
    Page<Department> searchDepartment(DepartmentSearchParam param, Pageable pageable);
}
