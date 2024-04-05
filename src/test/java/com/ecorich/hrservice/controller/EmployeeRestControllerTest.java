package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.dto.JobData;
import com.ecorich.hrservice.dto.SimpleEmployeeData;
import com.ecorich.hrservice.dto.request.EmployeeRequest;
import com.ecorich.hrservice.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 직원")
@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @MockBean
    private EmployeeService employeeService;

    @DisplayName("직원 아이디가 주어지면 직원 상세 조회시 직원의 상세 죄회 데이터가 호출된다.")
    @Test
    public void givenEmployeeId_whenRequestDetailEmployee_thenReturnsDetailEmployeeInfo() throws Exception {
        //given
        long employeeId = 3L;
        given(employeeService.getEmployee(any(Long.class))).willReturn(Optional.of(createEmployeeData(employeeId)));
        //when & then
        String result = mvc.perform(get("/employee/" + employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value(200))
                .andExpect(jsonPath("data.employeeId").value(employeeId))
                .andExpect(jsonPath("data.jobData").exists())
                .andReturn().getResponse().getContentAsString();
        then(employeeService).should().getEmployee(any(Long.class));
    }

    @DisplayName("직원 아이디와 파라미터가 주어지면 직원 정보를 수정한 후 수정된 직원 정보를 리턴한다.")
    @Test
    public void givenEmployeeIdAndUpdateParameter_whenUpdateEmployee_thenReturnsUpdatedEmployee() throws Exception {
        //given
        long employeeId = 3L;
        EmployeeRequest request = EmployeeRequest.builder()
                .commissionPct(4.34)
                .departmentId(4L)
                .managerId(3L)
                .email("test2@test.com")
                .salary(43.53)
                .phoneNumber("010-3434-3453")
                .firstName("test")
                .lastName("test")
                .managerId(1L)
                .jobId("TEST-AC")
                .departmentId(4L)
                .hireDate(LocalDate.parse("2023-11-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        given(employeeService.updateEmployee(any(Long.class),any(String.class),any(Long.class),any(Long.class),any(EmployeeData.class))).willReturn(createEmployeeData(employeeId));
        //when & then
        String result = mvc.perform(put("/employee/" + employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value(200))
                .andExpect(jsonPath("data.employeeId").value(employeeId))
                .andExpect(jsonPath("data.jobData").exists())
                .andReturn().getResponse().getContentAsString();
        then(employeeService).should().updateEmployee(any(Long.class),any(String.class),any(Long.class),any(Long.class),any(EmployeeData.class));
    }

    private EmployeeData createEmployeeData(Long employeeId) {
        return EmployeeData.builder()
                .employeeId(employeeId)
                .firstName("first")
                .lastName("last")
                .email("test@test.com")
                .phoneNumber("010-1234-5678")
                .hireDate(LocalDate.now())
                .jobData(JobData.builder()
                        .jobId("AC-DG")
                        .maxSalary(445L)
                        .minSalary(43L)
                        .title("game")
                        .build())
                .salary(4.34)
                .commissionPct(0.01)
                .department(DepartmentData.builder()
                        .departmentId(1L)
                        .departmentName("QA")
                        .build())
                .manager(SimpleEmployeeData.builder()
                        .employeeId(5L)
                        .build())
                .build();
    }
}
