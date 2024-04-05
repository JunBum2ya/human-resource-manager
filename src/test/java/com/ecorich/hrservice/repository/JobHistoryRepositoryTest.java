package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.JobHistory;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - 이력")
@SpringBootTest
public class JobHistoryRepositoryTest {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @DisplayName("파라미터와 페이지네이션으로 이력을 조회하면 이력이 페이징되어서 전달된다.")
    @Test
    public void givenParameterAndPageable_whenSelectJobHistory_thenReturnsJobHistoryPage() {
        //given
        JobHistorySearchParam param = JobHistorySearchParam.builder().employeeId(101L).build();
        Pageable pageable = Pageable.ofSize(10);
        //when
        Page<JobHistory> page = jobHistoryRepository.searchJobHistory(param,pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getContent()).hasSize(2);
    }

}
