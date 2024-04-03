package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.SimpleEmployeeData;
import com.ecorich.hrservice.dto.request.DepartmentSearchRequest;
import com.ecorich.hrservice.dto.request.UpdateDepartmentSalaryRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.DepartmentResponse;
import com.ecorich.hrservice.service.DepartmentService;
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
    @PutMapping("/{departmentId}")
    public ResponseEntity<CommonResponse<List<SimpleEmployeeData>>> updateSalaryInDepartment(
            @PathVariable Long departmentId,
            @Valid @RequestBody UpdateDepartmentSalaryRequest request
    ) {
        List<SimpleEmployeeData> employeeList = departmentService.updateDepartmentSalary(departmentId, request.getRate());
        return ResponseEntity.ok(CommonResponse.of(employeeList));
    }
}
