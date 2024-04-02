package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.JobHistoryData;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import com.ecorich.hrservice.dto.request.JobHistorySearchRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.service.JobHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/history")
@RestController
public class JobHistoryRestController {
    private final JobHistoryService jobHistoryService;

    /**
     * 이력 조회 엔드포인트
     *
     * @param employeeId
     * @param pageable
     * @return
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<CommonResponse<Page<JobHistoryData>>> searchJobHistory(
            @PathVariable Long employeeId,
            JobHistorySearchRequest request,
            @PageableDefault(size = 10, sort = {"startDate"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        JobHistorySearchParam param = request.toJobHistorySearchParam(employeeId);
        Page<JobHistoryData> page = jobHistoryService.searchJobHistory(param, pageable);
        return ResponseEntity.ok(CommonResponse.of(page));
    }
}
