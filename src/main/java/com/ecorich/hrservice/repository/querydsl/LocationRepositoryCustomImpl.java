package com.ecorich.hrservice.repository.querydsl;

import com.ecorich.hrservice.domain.Location;
import com.ecorich.hrservice.domain.QLocation;
import com.ecorich.hrservice.dto.param.LocationSearchParam;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class LocationRepositoryCustomImpl extends QuerydslRepositorySupport implements LocationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public LocationRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Location.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Location> searchLocation(LocationSearchParam param, Pageable pageable) {
        QLocation location = QLocation.location;
        JPAQuery<Location> query = jpaQueryFactory.selectFrom(location);
        query.where(
                location.id.isNotNull()
                        .and(param.getLocationId().map(location.id::eq).orElse(null))
                        .and(param.getCity().map(location.city::contains).orElse(null))
                        .and(param.getPostalCode().map(location.postalCode::eq).orElse(null))
                        .and(param.getStreetAddress().map(location.streetAddress::contains).orElse(null))
                        .and(param.getStateProvince().map(location.stateProvince::eq).orElse(null))
                        .and(param.getCountryId().map(location.country.id::eq).orElse(null))
                        .and(param.getRegionId().map(location.country.region.id::eq).orElse(null))
        );
        List<Location> content = Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query).fetch();
        return new PageImpl<Location>(content, pageable, query.fetchCount());
    }
}
