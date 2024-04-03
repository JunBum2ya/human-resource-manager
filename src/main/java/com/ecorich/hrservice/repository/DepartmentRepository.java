package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.repository.querydsl.DepartmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentRepositoryCustom {
}