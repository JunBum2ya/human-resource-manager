package com.ecorich.hrservice.repository.querydsl;

import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.domain.QDepartment;
import com.ecorich.hrservice.dto.param.DepartmentSearchParam;
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
public class DepartmentRepositoryCustomImpl extends QuerydslRepositorySupport implements DepartmentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public DepartmentRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Department.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Department> searchDepartment(DepartmentSearchParam param, Pageable pageable) {
        QDepartment department = QDepartment.department;
        JPAQuery<Department> query = jpaQueryFactory.selectFrom(department);
        query.where(
                department.isNotNull()
                        .and(param.getDepartmentId().map(department.id::eq).orElse(null))
                        .and(param.getDepartmentName().map(department.name::contains).orElse(null))
                        .and(param.getLocationId().map(department.location.id::eq).orElse(null))
        );
        List<Department> contents = Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query).fetch();
        return new PageImpl<Department>(contents,pageable,query.fetchCount());
    }
}
