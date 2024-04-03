package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}