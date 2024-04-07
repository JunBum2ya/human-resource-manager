package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.LocationDetailData;
import lombok.Builder;

/**
 * 위치 응답 포맷
 * @param locationId
 * @param streetAddress
 * @param postalCode
 * @param city
 * @param stateProvince
 * @param countryId
 * @param countryName
 * @param regionId
 * @param regionName
 */
@Builder
public record LocationDetailResponse(
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
    public static LocationDetailResponse from(LocationDetailData data) {
        return LocationDetailResponse.builder()
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
