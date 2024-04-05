package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.config.JpaConfig;
import com.ecorich.hrservice.domain.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - Employee")
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @DisplayName("Employee Id로 조회를 하면 Employee가 optional로 반환된다.")
    @Test
    public void givenEmployeeId_whenSelectEmployee_thenReturnsOptionalEmployee() {
        //given
        long employeeId = 200L;
        //when
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        //then
        assertThat(employee).isNotEmpty();
        assertThat(employee.get()).hasFieldOrPropertyWithValue("id",employeeId);
    }

    @DisplayName("Employee Id로 조회를 하면 Employee가 반환된다.")
    @Test
    public void givenEmployeeId_whenSelectEmployee_thenReturnsEmployee() {
        //given
        long employeeId = 200L;
        //when
        Employee employee = employeeRepository.getReferenceById(employeeId);
        //then
        assertThat(employee).isNotNull();
        assertThat(employee).hasFieldOrPropertyWithValue("id",employeeId);
    }

}
