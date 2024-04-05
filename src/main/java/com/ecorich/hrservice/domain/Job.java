package com.ecorich.hrservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "jobs")
@Entity
public class Job {
    @Id
    @Column(name = "job_id", nullable = false, length = 10)
    private String id;
    @Column(name = "job_title", nullable = false, length = 35)
    private String title;
    private Long minSalary;
    private Long maxSalary;
}
