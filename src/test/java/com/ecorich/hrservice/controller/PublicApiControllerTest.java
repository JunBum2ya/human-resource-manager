package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.service.PublicApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@DisplayName("View 컨트롤러 - 위치")
@WebMvcTest(PublicApiRestController.class)
public class PublicApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PublicApiService publicApiService;

    @DisplayName("키워드로 펫 동반 가능한 장소를 검색하면 페이징된 펫 동반 가능한 장소가 반환된다.")
    @Test
    public void givenKeyword_whenSearchPetAttract_thenReturnsPetAttractInfoPage() throws Exception {
        //given
        String keyword = "test";
        given(publicApiService.searchPetAttract(any(Integer.class),any(Integer.class),any(String.class))).willReturn(Page.empty());
        //when & then
        mvc.perform(get("/api/pet-attract")
                        .queryParam("keyword",keyword))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value(200))
                .andExpect(jsonPath("data.content").isEmpty());
        //then
        then(publicApiService).should().searchPetAttract(any(Integer.class),any(Integer.class),any(String.class));
    }

}
