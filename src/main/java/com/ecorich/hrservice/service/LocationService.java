package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.Location;
import com.ecorich.hrservice.dto.LocationDetailData;
import com.ecorich.hrservice.dto.param.LocationSearchParam;
import com.ecorich.hrservice.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    /**
     * 지역 조회 메소드
     * @param param
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<LocationDetailData> searchLocation(LocationSearchParam param, Pageable pageable) {
        Page<Location> page = locationRepository.searchLocation(param,pageable);
        return page.map(LocationDetailData::from);
    }

}
