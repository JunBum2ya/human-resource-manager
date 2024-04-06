package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.config.JpaConfig;
import com.ecorich.hrservice.domain.Country;
import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.domain.Location;
import com.ecorich.hrservice.domain.Region;
import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
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
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - Department")
@DataJpaTest
@Import(JpaConfig.class)
@ActiveProfiles("testdb")
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    private Long locationId = -1L;

    @BeforeEach
    public void initData() {
        Region region = TestCaseUtil.createRegion();
        Country country = TestCaseUtil.createCountry(region);
        Location location = locationRepository.save(TestCaseUtil.createLocation(country));
        Department department = TestCaseUtil.createDepartment(90L,location);
        departmentRepository.save(department);
        locationId = location.getId();
    }

    @DisplayName("파라미터와 페이지네이션으로 부서를 조회하면 paging된 부서가 반환된다.")
    @Test
    public void givenParameterAndPageable_whenSearchDepartment_thenReturnsDepartmentPage() {
        //given
        DepartmentSearchParam param = DepartmentSearchParam.builder()
                .locationId(locationId)
                .build();
        Pageable pageable = Pageable.ofSize(10);
        //when
        Page<Department> page = departmentRepository.searchDepartment(param, pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getTotalElements()).isEqualTo(1L);
        assertThat(page.getContent().size()).isEqualTo(1);
    }

    @DisplayName("부서 아이디로 조회를 하면 부서가 반환된다.")
    @Test
    public void givenDepartmentId_whenSelectDepartment_thenReturnsDepartmentAndAccessEmployeeInDepartment() {
        //given
        long departmentId = 90L;
        //when
        Department department = departmentRepository.getReferenceById(departmentId);
        //then
        assertThat(department).isNotNull();
        assertThat(department).hasFieldOrPropertyWithValue("id", departmentId);
    }

}
