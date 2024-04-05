package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.SimpleEmployeeData;
import com.ecorich.hrservice.dto.request.DepartmentSearchRequest;
import com.ecorich.hrservice.dto.request.UpdateDepartmentSalaryRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.DepartmentResponse;
import com.ecorich.hrservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "DEPARTMENT API", description = "부서 관련 API")
@RequiredArgsConstructor
@RequestMapping("/department")
@RestController
public class DepartmentRestController {

    private final DepartmentService departmentService;

    /**
     * 부서 조회 엔드포인트
     * @param request
     * @param pageable
     * @return
     */
    @Operation(summary = "부서 검색 API", description = "부서 검색 API")
    @Parameters({
            @Parameter(name = "departmentId", description = "부서 아이디(일치)", example = "1"),
            @Parameter(name = "departmentName", description = "부서명(부분일치)", example = "HR"),
            @Parameter(name = "locationId", description = "위치 아이디(일치)", example = "2")
    })
    @GetMapping
    public ResponseEntity<CommonResponse<Page<DepartmentResponse>>> searchDepartment(
            DepartmentSearchRequest request,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<DepartmentData> page = departmentService.searchDepartment(request.toDepartmentSearchParam(),pageable);
        return ResponseEntity.ok(CommonResponse.of(page.map(DepartmentResponse::from)));
    }

    /**
     * 부서별 일괄적으로 임금 인상
     * @param departmentId
     * @param request
     * @return
     */
    @Operation(summary = "임금 일괄 수정", description = "부서내 직원들의 임금을 일괄적으로 인상")
    @Parameters({
            @Parameter(name = "rate", description = "임금 인상 비율", example = "4.3", required = true)
    })
    @PutMapping("/{departmentId}")
    public ResponseEntity<CommonResponse<List<SimpleEmployeeData>>> updateSalaryInDepartment(
            @Parameter(name = "departmentId", description = "부서 아이디", example = "3", required = true) @PathVariable Long departmentId,
            @Valid @RequestBody UpdateDepartmentSalaryRequest request
    ) {
        List<SimpleEmployeeData> employeeList = departmentService.updateDepartmentSalary(departmentId, request.getRate());
        return ResponseEntity.ok(CommonResponse.of(employeeList));
    }
}
