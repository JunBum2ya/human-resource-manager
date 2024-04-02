package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.JobHistory;
import com.ecorich.hrservice.repository.querydsl.JobHistoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long>, JobHistoryRepositoryCustom {
}