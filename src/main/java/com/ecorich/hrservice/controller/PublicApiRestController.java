package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.PetAttractItem;
import com.ecorich.hrservice.dto.request.PetAttractRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.PetAttractResponse;
import com.ecorich.hrservice.service.PublicApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PUBLIC API", description = "공공데이터 활용 API")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class PublicApiRestController {

    private final PublicApiService publicApiService;

    /**
     * 동물 편의 시설 조회 API
     * @param request
     * @return
     */
    @Operation(summary = "반려동물 동행가능한 장소 API", description = "반려동물 동행가능한 장소 조회 API")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호", example = "1"),
            @Parameter(name = "size", description = "페이지 크기", example = "10"),
            @Parameter(name = "keyword", description = "제목 검색 파라미터")
    })
    @GetMapping("/pet-attract")
    public ResponseEntity<CommonResponse<Page<PetAttractResponse>>> searchPetAttract(@Parameter(hidden = true) PetAttractRequest request) {
        Page<PetAttractItem> page = publicApiService.searchPetAttract(request.page(), request.size(), request.keyword());
        return ResponseEntity.ok(CommonResponse.of(page.map(PetAttractResponse::from)));
    }

}
