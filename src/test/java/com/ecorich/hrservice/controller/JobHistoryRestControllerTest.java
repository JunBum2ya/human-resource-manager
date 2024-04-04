package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import com.ecorich.hrservice.service.JobHistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 이력")
@WebMvcTest(JobHistoryRestController.class)
public class JobHistoryRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JobHistoryService jobHistoryService;

    @DisplayName("직원 아이디와 검색 파라미터를 사용하여 이력을 조회하면 이력이 페이징되어서 전달 된다.")
    @Test
    public void givenEmployeeIdAndParameter_whenSearchJobHistory_thenReturnsJobHistoryDataPage() throws Exception {
        //given
        long employeeId = 4L;
        String startDate = "2024-04-01";
        String endDate = "2023-04-04";
        given(jobHistoryService.searchJobHistory(any(JobHistorySearchParam.class),any(Pageable.class))).willReturn(Page.empty());
        //when & then
        mvc.perform(get("/history/" + employeeId)
                .queryParam("startDate",startDate)
                .queryParam("endDate",endDate))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value(200))
                .andExpect(jsonPath("data.content").isEmpty());
        then(jobHistoryService).should().searchJobHistory(any(JobHistorySearchParam.class),any(Pageable.class));
    }

}
