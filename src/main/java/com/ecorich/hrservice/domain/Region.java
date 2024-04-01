package com.ecorich.hrservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 대륙
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "regions")
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id", nullable = false)
    private Long id;
    @Column(name = "region_name",length = 25)
    private String name;

    /**
     * 팩토리 메소드
     */
    public static Region of(String name) {
        return Region.builder()
                .name(name)
                .build();
    }
}
