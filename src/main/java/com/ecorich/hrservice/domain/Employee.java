package com.ecorich.hrservice.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "employees")
@Entity
public class Employee {
    @Id
    @Column(name = "employee_id", nullable = false)
    private Long id;
    @Column(length = 20)
    private String firstName;
    @Column(length = 25, nullable = false)
    private String lastName;
    @Column(length = 25, nullable = false)
    private String email;
    @Column(length = 20)
    private String phoneNumber;
    @Column(nullable = false)
    private LocalDate hireDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(nullable = false)
    private Double salary;
    private Double commissionPct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
