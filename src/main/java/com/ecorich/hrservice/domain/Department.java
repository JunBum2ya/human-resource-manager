package com.ecorich.hrservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "departments")
@Entity
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    private Long id;
    @Column(name = "department_name", nullable = false, length = 30)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    /**
     * 팩토리 메소드
     * @param id
     * @param name
     * @param manager
     * @param location
     * @return
     */
    public static Department of(Long id, String name, Employee manager, Location location) {
        return Department.builder()
                .id(id)
                .name(name)
                .manager(manager)
                .location(location)
                .build();
    }
}
