package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Location;
import lombok.Builder;

@Builder
public record LocationDetailData(Long locationId, String streetAddress, String postalCode, String city,
                                 String stateProvince, CountryWithRegionData country) {

    public static LocationDetailData from(Location location) {
        return LocationDetailData.builder()
                .locationId(location.getId())
                .streetAddress(location.getStreetAddress())
                .postalCode(location.getPostalCode())
                .city(location.getCity())
                .country(CountryWithRegionData.from(location.getCountry()))
                .build();
    }
}
