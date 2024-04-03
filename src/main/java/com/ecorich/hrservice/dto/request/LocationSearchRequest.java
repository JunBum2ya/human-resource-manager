package com.ecorich.hrservice.dto.request;

import com.ecorich.hrservice.dto.param.LocationSearchParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LocationSearchRequest {
    private Long locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryId;
    private Long regionId;

    public LocationSearchParam toLocationSearchParam() {
        return LocationSearchParam.builder()
                .locationId(locationId)
                .streetAddress(streetAddress)
                .postalCode(postalCode)
                .city(city)
                .stateProvince(stateProvince)
                .countryId(countryId)
                .regionId(regionId)
                .build();
    }
}
