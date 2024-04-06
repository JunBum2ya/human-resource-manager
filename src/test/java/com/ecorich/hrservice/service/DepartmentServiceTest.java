package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.*;
import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
import com.ecorich.hrservice.repository.DepartmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 부서")
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService sut;

    @Mock
    private DepartmentRepository departmentRepository;

    @DisplayName("부서 검색 파라미터로 부서를 검색하면 페이징된 부서 정보가 반환된다.")
    @Test
    public void givenDepartSearchParamAndPageable_whenSearchDepartment_thenReturnsDepartmentDataPage() {
        //given
        DepartmentSearchParam param = DepartmentSearchParam.builder()
                .departmentId(3L)
                .departmentName("HR")
                .locationId(1L)
                .build();
        Pageable pageable = Pageable.ofSize(10);
        given(departmentRepository.searchDepartment(any(DepartmentSearchParam.class),any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(createDepartment(1L),createDepartment(2L)),pageable,2));
        //when
        Page<DepartmentData> page = sut.searchDepartment(param,pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getSize()).isEqualTo(10);
        then(departmentRepository).should().searchDepartment(any(DepartmentSearchParam.class),any(Pageable.class));
    }

    @DisplayName("부서 아이디와 인상 비율을 입력하여 부서내 직원들의 임금을 인상한다면 인상된 직원 리스트가 반환된다.")
    @Test
    public void givenDepartmentIdAndRate_whenUpdateEmployeeSalaryInDepartment_thenReturnsEmployeeList() {
        //given
        long departmentId = 1L;
        double rate = 50.0;
        given(departmentRepository.getReferenceById(any(Long.class))).willReturn(createDepartment(3L));
        //when
        List<EmployeeData> employeeDataList = sut.updateDepartmentSalary(departmentId,rate);
        //then
        assertThat(employeeDataList).isNotEmpty();
        assertThat(employeeDataList).hasSize(2);
        assertThat(employeeDataList).allMatch(data -> data.salary() == 450.0);
        then(departmentRepository).should().getReferenceById(any(Long.class));
    }

    private Department createDepartment(long departmentId) {
        Department department = Department.builder()
                .id(departmentId)
                .name("HR")
                .employeeList(new ArrayList<>())
                .location(Location.builder()
                        .id(3L)
                        .streetAddress("서울시")
                        .postalCode("1334-34")
                        .city("SEOUL")
                        .stateProvince("seoul")
                        .country(Country.builder()
                                .id("KR")
                                .name("대한민국")
                                .region(Region.builder()
                                        .id(3L)
                                        .name("ASIA")
                                        .build())
                                .build())
                        .build())
                .build();
        department.getEmployeeList().add(Employee.builder().salary(300.0).build());
        department.getEmployeeList().add(Employee.builder().salary(300.0).build());
        return department;
    }

}
