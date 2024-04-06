package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.config.JpaConfig;
import com.ecorich.hrservice.domain.*;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import com.ecorich.hrservice.util.TestCaseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - 이력")
@Import(JpaConfig.class)
@DataJpaTest
@ActiveProfiles("testdb")
public class JobHistoryRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void initData() {
        Region region = TestCaseUtil.createRegion();
        Country country = TestCaseUtil.createCountry(region);
        Location location = locationRepository.save(TestCaseUtil.createLocation(country));
        Department department = departmentRepository.save(TestCaseUtil.createDepartment(100L, location));
        Job job = jobRepository.save(TestCaseUtil.createJob("AD-TEST"));
        Employee employee = employeeRepository.save(TestCaseUtil.createManager(200L, job));
        JobHistory jobHistory = JobHistory.builder()
                .department(department)
                .employee(employee)
                .job(job)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();
        jobHistoryRepository.save(jobHistory);
    }

    @DisplayName("파라미터와 페이지네이션으로 이력을 조회하면 이력이 페이징되어서 전달된다.")
    @Test
    public void givenParameterAndPageable_whenSelectJobHistory_thenReturnsJobHistoryPage() {
        //given
        JobHistorySearchParam param = JobHistorySearchParam.builder().employeeId(200L).build();
        Pageable pageable = Pageable.ofSize(10);
        //when
        Page<JobHistory> page = jobHistoryRepository.searchJobHistory(param, pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getContent()).hasSize(1);
    }

}
