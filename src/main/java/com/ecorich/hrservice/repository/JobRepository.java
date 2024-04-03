package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}