package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
import com.ecorich.hrservice.dto.request.DepartmentSearchRequest;
import com.ecorich.hrservice.dto.request.UpdateDepartmentSalaryRequest;
import com.ecorich.hrservice.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 부서")
@WebMvcTest(DepartmentRestController.class)
public class DepartmentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private DepartmentService departmentService;

    @DisplayName("파라미터를 사용하여 부서를 검색하면 부서정보가 페이징 되어 전달된다.")
    @Test
    public void givenRequestParameter_whenSearchDepartment_thenReturnsDepartmentPageInfo() throws Exception {
        //given
        given(departmentService.searchDepartment(any(DepartmentSearchParam.class), any(Pageable.class))).willReturn(Page.empty());
        //when & then
        String result = mvc.perform(get("/department")
                        .queryParam("departmentId", String.valueOf(4L))
                        .queryParam("locationId", String.valueOf(43L)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"))
                .andExpect(jsonPath("data.content").isEmpty())
                .andExpect(jsonPath("data.totalElements").value(0))
                .andReturn().getResponse().getContentAsString();
        then(departmentService).should().searchDepartment(any(DepartmentSearchParam.class), any(Pageable.class));
    }

    @DisplayName("부서 아이디와 인상 비율을 입력하여 부서 내 직원의 임금을 수정하면 수정된 직원들의 목록이 반환된다.")
    @Test
    public void givenDepartmentIdAndRequestBody_whenUpdateSalaryInDepartment_thenReturnsEmployeeList() throws Exception {
        //given
        long departmentId = 3L;
        String body = objectMapper.writeValueAsString(UpdateDepartmentSalaryRequest.builder().rate(43.4).build());
        given(departmentService.updateDepartmentSalary(any(Long.class),any(Double.class))).willReturn(new ArrayList<>());
        //when & then
        String result = mvc.perform(put("/department/" + departmentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        then(departmentService).should().updateDepartmentSalary(any(Long.class),any(Double.class));
    }

}
