package com.ecorich.hrservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 대륙
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "regions")
@Entity
public class Region {
    @Id
    @Column(name = "region_id", nullable = false)
    private Long id;
    @Column(name = "region_name",length = 25)
    private String name;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Country> countries = new LinkedList<>();

    /**
     * 팩토리 메소드
     */
    public static Region of(Long id, String name) {
        return Region.builder()
                .id(id)
                .name(name)
                .build();
    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }
}
