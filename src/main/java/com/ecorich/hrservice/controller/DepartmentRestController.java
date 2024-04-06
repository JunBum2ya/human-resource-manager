package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.EmployeeData;
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
            @Parameter(name = "locationId", description = "위치 아이디(일치)", example = "2"),
            @Parameter(name = "page", description = "페이지 번호", example = "0"),
            @Parameter(name = "size", description = "페이지 크기", example = "10")
    })
    @GetMapping
    public ResponseEntity<CommonResponse<Page<DepartmentResponse>>> searchDepartment(
            @Parameter(hidden = true) DepartmentSearchRequest request,
            @Parameter(hidden = true) @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
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
    @PutMapping("/{departmentId}")
    public ResponseEntity<CommonResponse<List<EmployeeData>>> updateSalaryInDepartment(
            @Parameter(name = "departmentId", description = "부서 아이디", example = "10", required = true) @PathVariable Long departmentId,
            @Valid @RequestBody UpdateDepartmentSalaryRequest request
    ) {
        List<EmployeeData> employeeList = departmentService.updateDepartmentSalary(departmentId, request.getRate());
        return ResponseEntity.ok(CommonResponse.of(employeeList));
    }
}
