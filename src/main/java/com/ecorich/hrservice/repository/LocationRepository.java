package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Location;
import com.ecorich.hrservice.repository.querydsl.LocationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>, LocationRepositoryCustom {
}