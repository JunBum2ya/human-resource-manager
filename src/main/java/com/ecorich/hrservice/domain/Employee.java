package com.ecorich.hrservice.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
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

    public void update(String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate, Job job, Double salary, Double commissionPct, Employee manager, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.job = job;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.manager = manager;
        this.department = department;
    }

}
