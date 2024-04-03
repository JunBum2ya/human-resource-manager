package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.domain.Employee;
import com.ecorich.hrservice.dto.SimpleEmployeeData;
import com.ecorich.hrservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

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
