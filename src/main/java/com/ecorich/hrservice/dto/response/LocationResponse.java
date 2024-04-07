package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.LocationDetailData;
import lombok.Builder;

@Builder
public record LocationResponse(
        Long locationId,
        String streetAddress,
        String postalCode,
        String city,
        String stateProvince,
        String countryId,
        String countryName,
        Long regionId,
        String regionName
) {
    public static LocationResponse from(LocationDetailData data) {
        return LocationResponse.builder()
                .locationId(data.locationId())
                .streetAddress(data.streetAddress())
                .postalCode(data.postalCode())
                .city(data.city())
                .stateProvince(data.stateProvince())
                .countryId(data.country().countryId())
                .countryName(data.country().countryName())
                .regionId(data.country().region().regionId())
                .regionName(data.country().region().regionName())
                .build();
    }
}
