package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.JobHistory;
import com.ecorich.hrservice.dto.JobHistoryData;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import com.ecorich.hrservice.repository.JobHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class JobHistoryService {
    private final JobHistoryRepository jobHistoryRepository;

    /**
     * 이력 죄회 메소드
     * @param param
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<JobHistoryData> searchJobHistory(JobHistorySearchParam param, Pageable pageable) {
        Page<JobHistory> jobHistoryPage = jobHistoryRepository.searchJobHistory(param,pageable);
        return jobHistoryPage.map(JobHistoryData::from);
    }
}
