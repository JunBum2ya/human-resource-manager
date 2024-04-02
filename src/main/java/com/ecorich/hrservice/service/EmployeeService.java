package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.Employee;
import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Transactional(readOnly = true)
    public Optional<EmployeeData> getEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.map(EmployeeData::from);
    }
}
