package com.ecorich.hrservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "locations")
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Long id;
    @Column(length = 40)
    private String streetAddress;
    @Column(length = 12)
    private String postalCode;
    @Column(length = 30, nullable = false)
    private String city;
    @Column(length = 25)
    private String stateProvince;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id")
    private Country country;

    /**
     * 팩토리 메소드
     * @param streetAddress
     * @param postalCode
     * @param city
     * @param stateProvince
     * @param country
     * @return
     */
    public static Location of(String streetAddress, String postalCode, String city, String stateProvince, Country country) {
        return Location.builder()
                .streetAddress(streetAddress)
                .postalCode(postalCode)
                .city(city)
                .stateProvince(stateProvince)
                .country(country)
                .build();
    }
}
