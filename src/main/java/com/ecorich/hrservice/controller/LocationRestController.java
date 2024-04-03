package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.LocationWithCountryAndRegionData;
import com.ecorich.hrservice.dto.request.LocationSearchRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.LocationResponse;
import com.ecorich.hrservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping
    public ResponseEntity<CommonResponse<Page<LocationResponse>>> searchLocation(
            LocationSearchRequest request,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<LocationWithCountryAndRegionData> page = locationService.searchLocation(request.toLocationSearchParam(),pageable);
        return ResponseEntity.ok(CommonResponse.of(page.map(LocationResponse::from)));
    }

}
