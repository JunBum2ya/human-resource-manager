package com.ecorich.hrservice.repository.querydsl;

import com.ecorich.hrservice.domain.JobHistory;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobHistoryRepositoryCustom {
    Page<JobHistory> searchJobHistory(JobHistorySearchParam param, Pageable pageable);
}
