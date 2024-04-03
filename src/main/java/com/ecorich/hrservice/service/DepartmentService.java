package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.domain.Employee;
import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.SimpleEmployeeData;
import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
import com.ecorich.hrservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    /**
     * 부서 조회 메소드
     * @param param
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<DepartmentData> searchDepartment(DepartmentSearchParam param, Pageable pageable) {
        Page<Department> page = departmentRepository.searchDepartment(param,pageable);
        return page.map(DepartmentData::from);
    }

    /**
     * 임금 일괄 인상
     * @param departmentId
     * @param rate
     * @return
     */
    public List<SimpleEmployeeData> updateDepartmentSalary(Long departmentId, Double rate) {
        Department department = departmentRepository.getReferenceById(departmentId);
        department.getEmployeeList().forEach(employee -> employee.updateSalary(rate));
        return department.getEmployeeList().stream().map(SimpleEmployeeData::from).toList();
    }
}
