package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.param.LocationSearchParam;
import com.ecorich.hrservice.service.LocationService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@DisplayName("View 컨트롤러 - 위치")
@WebMvcTest(LocationRestController.class)
public class LocationRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LocationService locationService;

    @DisplayName("검색 파라미터로 위치를 조회하면 페이징된 위치 정보가 반환된다.")
    @Test
    public void givenParameters_whenSearchLocations_thenReturnsLocationPage() throws Exception {
        //given
        String postalCode = "434-345";
        String city = "test";
        given(locationService.searchLocation(any(LocationSearchParam.class),any(Pageable.class))).willReturn(Page.empty());
        //when & then
        mvc.perform(get("/location")
                        .queryParam("postalCode",postalCode)
                        .queryParam("city",city))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value(200))
                .andExpect(jsonPath("data.content").isEmpty());
        then(locationService).should().searchLocation(any(LocationSearchParam.class),any(Pageable.class));
    }

}
