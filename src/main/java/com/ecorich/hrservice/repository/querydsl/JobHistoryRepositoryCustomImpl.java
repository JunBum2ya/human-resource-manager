package com.ecorich.hrservice.repository.querydsl;

import com.ecorich.hrservice.domain.*;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class JobHistoryRepositoryCustomImpl extends QuerydslRepositorySupport implements JobHistoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public JobHistoryRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(JobHistory.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
     * 이력 조회
     * @param param
     * @param pageable
     * @return
     */
    @Override
    public Page<JobHistory> searchJobHistory(JobHistorySearchParam param, Pageable pageable) {
        QJobHistory jobHistory = QJobHistory.jobHistory;
        JPAQuery<JobHistory> query = jpaQueryFactory.selectFrom(jobHistory)
                .where(buildWhereClause(param));
        JPAQuery<Long> countQuery = jpaQueryFactory.select(jobHistory.count())
                .from(jobHistory)
                .where(buildWhereClause(param));
        List<JobHistory> jobHistoryList = Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query).fetch();
        return new PageImpl<JobHistory>(jobHistoryList, pageable, countQuery.fetch().size());
    }

    /**
     * Where 절 구현
     * @param param
     * @return
     */
    private Predicate buildWhereClause(JobHistorySearchParam param) {
        QJobHistory jobHistory = QJobHistory.jobHistory;
        return jobHistory.employee.id.eq(param.getEmployeeId())
                .and(param.getStartDate().map(jobHistory.startDate::after).orElse(null))
                .and(param.getEndDate().map(jobHistory.endDate::before).orElse(null));
    }
}
