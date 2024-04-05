package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.Employee;
import com.ecorich.hrservice.domain.Job;
import com.ecorich.hrservice.domain.JobHistory;
import com.ecorich.hrservice.dto.JobHistoryData;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import com.ecorich.hrservice.repository.JobHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 이력")
@ExtendWith(MockitoExtension.class)
public class JobHistoryServiceTest {

    @InjectMocks
    private JobHistoryService sut;

    @Mock
    private JobHistoryRepository jobHistoryRepository;

    @DisplayName("파라미터와 페이징 정보로 이력을 조회하면 이력이 페이징되어서 반환된다.")
    @Test
    public void givenParameterAndPageable_whenSearchJobHistory_thenJobHistoryDataPage() {
        //given
        JobHistorySearchParam param = JobHistorySearchParam.builder()
                .employeeId(3L)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now())
                .build();
        Pageable pageable = Pageable.ofSize(10);
        given(jobHistoryRepository.searchJobHistory(any(JobHistorySearchParam.class),any(Pageable.class)))
                .willReturn(createJobHistoryPage());
        //when
        Page<JobHistoryData> page = sut.searchJobHistory(param, pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getTotalPages()).isEqualTo(1);
        then(jobHistoryRepository).should().searchJobHistory(any(JobHistorySearchParam.class),any(Pageable.class));
    }

    public Page<JobHistory> createJobHistoryPage() {
        JobHistory jobHistory = JobHistory.builder()
                .id(1L)
                .job(Job.builder()
                        .id("TEST-AD")
                        .title("TEST")
                        .maxSalary(3L)
                        .minSalary(0L)
                        .build())
                .employee(Employee.builder()
                        .id(3L)
                        .build())
                .build();
        return new PageImpl<JobHistory>(List.of(jobHistory),Pageable.ofSize(10),1);
    }

}
