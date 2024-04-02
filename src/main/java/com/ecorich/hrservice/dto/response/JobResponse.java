package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.JobData;
import lombok.Builder;

@Builder
public record JobResponse(String jobId, String title, Long minSalary, Long maxSalary) {
    public static JobResponse from(JobData jobData) {
        return JobResponse.builder()
                .jobId(jobData.jobId())
                .title(jobData.title())
                .minSalary(jobData.minSalary())
                .maxSalary(jobData.maxSalary())
                .build();
    }
}
