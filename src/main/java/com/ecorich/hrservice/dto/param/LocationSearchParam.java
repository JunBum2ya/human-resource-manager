package com.ecorich.hrservice.dto.param;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Optional;

@AllArgsConstructor
@Builder
public class LocationSearchParam {
    private Long locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryId;
    private Long regionId;

    public Optional<Long> getLocationId() {
        return Optional.ofNullable(locationId);
    }

    public Optional<String> getStreetAddress() {
        return Optional.ofNullable(streetAddress);
    }

    public Optional<String> getPostalCode() {
        return Optional.ofNullable(postalCode);
    }

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    public Optional<String> getStateProvince() {
        return Optional.ofNullable(stateProvince);
    }

    public Optional<String> getCountryId() {
        return Optional.ofNullable(countryId);
    }

    public Optional<Long> getRegionId() {
        return Optional.ofNullable(regionId);
    }
}
