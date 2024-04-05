package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.config.JpaConfig;
import com.ecorich.hrservice.domain.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - 역할")
@DataJpaTest
@Import(JpaConfig.class)
@ActiveProfiles("testdb")
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @BeforeEach
    public void initJobData() {
        jobRepository.save(Job.builder()
                        .id("AD_PRES")
                        .title("TEST")
                        .maxSalary(40000L)
                        .minSalary(20000L)
                .build());
    }

    @DisplayName("JobId로 조회를 하면 Job이 반환된다.")
    @Test
    public void givenJobId_whenSelectJob_thenReturnsJob() {
        //given
        String jobId = "AD_PRES";
        //when
        Job job = jobRepository.getReferenceById(jobId);
        //then
        assertThat(job).isNotNull();
        assertThat(job.getId()).isEqualTo("AD_PRES");
        assertThat(job.getTitle()).isNotNull();
    }

}
