package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Job;
import lombok.Builder;

/**
 * 임무 DTO
 * @param jobId
 * @param title
 * @param minSalary
 * @param maxSalary
 */
@Builder
public record JobData(String jobId, String title, Long minSalary, Long maxSalary) {
    public static JobData from(Job job) {
        return JobData.builder()
                .jobId(job.getId())
                .title(job.getTitle())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .build();
    }
}
