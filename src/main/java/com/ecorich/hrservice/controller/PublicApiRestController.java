package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.PetAttractItem;
import com.ecorich.hrservice.dto.request.PetAttractRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.PetAttractResponse;
import com.ecorich.hrservice.service.PublicApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/pet-attract")
    public ResponseEntity<CommonResponse<Page<PetAttractResponse>>> searchPetAttract(PetAttractRequest request) {
        Page<PetAttractItem> page = publicApiService.searchPetAttract(request.page(), request.size(), request.keyword());
        return ResponseEntity.ok(CommonResponse.of(page.map(PetAttractResponse::from)));
    }

}
