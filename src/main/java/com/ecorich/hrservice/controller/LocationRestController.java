package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.LocationDetailData;
import com.ecorich.hrservice.dto.request.LocationSearchRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.LocationDetailResponse;
import com.ecorich.hrservice.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "LOCATION API", description = "위치 API")
@RequiredArgsConstructor
@RequestMapping("/location")
@RestController
public class LocationRestController {

    private final LocationService locationService;

    /**
     * 지역 검색 쿼리
     * @param request
     * @param pageable
     * @return
     */
    @Operation(summary = "위치 검색 API", description = "파라미터를 사용하여 위치 정보 검색")
    @Parameters({
            @Parameter(name = "locationId", description = "위치 아이디"),
            @Parameter(name = "streetAddress", description = "도로명 주소"),
            @Parameter(name = "postalCode", description = "우편번호"),
            @Parameter(name = "city", description = "도시명"),
            @Parameter(name = "stateProvince", description = "시도명"),
            @Parameter(name = "countryId", description = "국가 아이디"),
            @Parameter(name = "regionId", description = "대륙 아이디"),
            @Parameter(name = "page",description = "페이지 번호", example = "0"),
            @Parameter(name = "size",description = "페이지 크기", example = "10")
    })
    @GetMapping
    public ResponseEntity<CommonResponse<Page<LocationDetailResponse>>> searchLocation(
            @Parameter(hidden = true) LocationSearchRequest request,
            @Parameter(hidden = true) @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<LocationDetailData> page = locationService.searchLocation(request.toLocationSearchParam(),pageable);
        return ResponseEntity.ok(CommonResponse.of(page.map(LocationDetailResponse::from)));
    }

}
