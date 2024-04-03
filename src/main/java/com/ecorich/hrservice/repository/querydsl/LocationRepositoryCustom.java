package com.ecorich.hrservice.repository.querydsl;

import com.ecorich.hrservice.domain.Location;
import com.ecorich.hrservice.dto.param.LocationSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationRepositoryCustom {
    Page<Location> searchLocation(LocationSearchParam param, Pageable pageable);
}
