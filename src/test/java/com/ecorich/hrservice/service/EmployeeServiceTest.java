package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.*;
import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.repository.DepartmentRepository;
import com.ecorich.hrservice.repository.EmployeeRepository;
import com.ecorich.hrservice.repository.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@DisplayName("비즈니스 로직 - 직원")
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService sut;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    @DisplayName("직원 아이디가 주어졌을 때 직원을 검색했을 경우 EmployeeData가 optional로 반환된다.")
    @Test
    public void givenEmployeeId_whenSearchEmployeeId_thenEmployeeDataOptional() {
        //given
        long employeeId = 3L;
        given(employeeRepository.findById(any(Long.class))).willReturn(Optional.of(createEmployee(employeeId)));
        //when
        Optional<EmployeeData> employeeData = sut.getEmployee(employeeId);
        //then
        assertThat(employeeData).isNotEmpty();
        assertThat(employeeData.get().employeeId()).isEqualTo(employeeId);
        assertThat(employeeData.get().jobData()).isNotNull();
        then(employeeRepository).should().findById(any(Long.class));
    }

    @DisplayName("update 파라미터로 직원을 수정하면 수정된 직원데이터가 반환된다.")
    @Test
    public void givenUpdateParameters_whenUpdateEmployee_thenReturnsEmployeeData() {
        //given
        long employeeId = 3L;
        String jobId = "test-3df";
        long managerId = 1L;
        long departmentId = 4L;
        EmployeeData updateParameter = EmployeeData.from(createEmployee(employeeId));
        given(employeeRepository.getReferenceById(any(Long.class))).willReturn(createEmployee(employeeId));
        given(jobRepository.getReferenceById(any(String.class))).willReturn(createJob(jobId));
        given(departmentRepository.getReferenceById(any(Long.class))).willReturn(createDepartment(departmentId));
        //when
        EmployeeData employeeData = sut.updateEmployee(employeeId,jobId,managerId,departmentId,updateParameter);
        //then
        assertThat(employeeData).isNotNull();
        assertThat(employeeData.employeeId()).isEqualTo(employeeId);
        assertThat(employeeData.jobData()).hasFieldOrPropertyWithValue("jobId",jobId);
        assertThat(employeeData.manager()).isNotNull();
        assertThat(employeeData.department()).hasFieldOrPropertyWithValue("departmentId",departmentId);
        then(employeeRepository).should(times(2)).getReferenceById(any(Long.class));
        then(jobRepository).should().getReferenceById(any(String.class));
        then(departmentRepository).should().getReferenceById(any(Long.class));
    }

    private Employee createEmployee(long employeeId) {
        return Employee.builder()
                .id(employeeId)
                .firstName("test")
                .hireDate(LocalDate.now())
                .job(createJob("TEST_AD"))
                .build();
    }

    private Job createJob(String jobId) {
        return Job.builder()
                .id(jobId)
                .title("TEST-TITLE")
                .maxSalary(421L)
                .minSalary(22L)
                .build();
    }

    private Department createDepartment(long departmentId) {
        return Department.builder()
                .id(departmentId)
                .name("HR")
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
    }

}
