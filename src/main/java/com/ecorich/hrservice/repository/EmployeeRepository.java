package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}