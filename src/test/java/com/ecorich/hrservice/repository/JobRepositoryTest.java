package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Job;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - 역할")
@SpringBootTest
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @DisplayName("JobId로 조회를 하면 Job이 반환된다.")
    @Test
    public void givenJobId_whenSelectJob_thenReturnsJob() {
        //given
        String jobId = "AD_PRES";
        //when
        Job job = jobRepository.getReferenceById(jobId);
        //then
        assertThat(job).isNotNull();
    }

}
