package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Region;
import lombok.Builder;

@Builder
public record RegionData(Long regionId, String regionName) {
    public static RegionData from(Region region) {
        return RegionData.builder()
                .regionId(region.getId())
                .regionName(region.getName())
                .build();
    }
}
