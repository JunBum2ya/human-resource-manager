package com.ecorich.hrservice.dto;

import com.ecorich.hrservice.domain.Country;
import lombok.Builder;

@Builder
public record CountryWithRegionData(String countryId, String countryName,RegionData region) {
    public static CountryWithRegionData from(Country country) {
        return CountryWithRegionData.builder()
                .countryId(country.getId())
                .countryName(country.getName())
                .region(RegionData.from(country.getRegion()))
                .build();
    }
}
