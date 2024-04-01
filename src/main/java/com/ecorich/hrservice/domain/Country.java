package com.ecorich.hrservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "countries")
@Entity
public class Country {
    @Id
    @Column(name = "country_id", length = 2, nullable = false)
    private String id;
    @Column(name = "country_name", length = 40)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id")
    private Region region;

    /**
     * 팩토리 메소드
     * @param id
     * @param name
     * @param region
     * @return
     */
    public static Country of(String id, String name, Region region) {
        Country country = Country.builder()
                .id(id)
                .name(name)
                .build();
        region.addCountry(country);
        return country;
    }
}
