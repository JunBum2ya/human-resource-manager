package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - Department")
@Transactional
@SpringBootTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @DisplayName("파라미터와 페이지네이션으로 부서를 조회하면 paging된 부서가 반환된다.")
    @Test
    public void givenParameterAndPageable_whenSearchDepartment_thenReturnsDepartmentPage() {
        //given
        DepartmentSearchParam param = DepartmentSearchParam.builder()
                .locationId(1700L)
                .build();
        Pageable pageable = Pageable.ofSize(10);
        //when
        Page<Department> page = departmentRepository.searchDepartment(param,pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getTotalElements()).isEqualTo(21L);
        assertThat(page.getContent().size()).isEqualTo(10);
    }

    @DisplayName("부서 아이디로 조회를 하면 부서가 반환되고 부서내 직원들에 접근할 수 있다.")
    @Test
    public void givenDepartmentId_whenSelectDepartment_thenReturnsDepartmentAndAccessEmployeeInDepartment() {
        //given
        long departmentId = 90;
        //when
        Department department = departmentRepository.getReferenceById(departmentId);
        //then
        assertThat(department).isNotNull();
        assertThat(department).hasFieldOrPropertyWithValue("id",departmentId);
        assertThat(department.getEmployeeList()).hasSize(3);
    }

}
