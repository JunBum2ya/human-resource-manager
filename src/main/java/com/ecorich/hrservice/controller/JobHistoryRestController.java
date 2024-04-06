package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.JobHistoryData;
import com.ecorich.hrservice.dto.param.JobHistorySearchParam;
import com.ecorich.hrservice.dto.request.JobHistorySearchRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.service.JobHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "JOB HISTORY API", description = "이력 API")
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
    @Operation(summary = "이력 조회 API", description = "직원의 이력 중 개시일자와 종료일자의 범위로 조회한다.")
    @Parameters({
            @Parameter(name = "startDate", description = "개시일자 한계", example = "2023-12-01"),
            @Parameter(name = "endDate", description = "종료일자 한계", example = "2024-01-01"),
            @Parameter(name = "page", description = "페이지 번호", example = "0"),
            @Parameter(name = "size", description = "페이지 크기", example = "10")
    })
    @GetMapping("/{employeeId}")
    public ResponseEntity<CommonResponse<Page<JobHistoryData>>> searchJobHistory(
            @Parameter(name = "employeeId", description = "직원 아이디", required = true) @PathVariable Long employeeId,
            @Parameter(hidden = true) JobHistorySearchRequest request,
            @Parameter(hidden = true) @PageableDefault(size = 10, sort = {"startDate"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        JobHistorySearchParam param = request.toJobHistorySearchParam(employeeId);
        Page<JobHistoryData> page = jobHistoryService.searchJobHistory(param, pageable);
        return ResponseEntity.ok(CommonResponse.of(page));
    }
}
