package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.dto.request.EmployeeRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.EmployeeResponse;
import com.ecorich.hrservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 직원 조회 API
 */
@RequestMapping("/employee")
@RequiredArgsConstructor
@Tag(name = "EMPLOYEE API", description = "직원 관련 API")
@RestController
public class EmployeeRestController {

    private final EmployeeService employeeService;

    /**
     * 직원 상세 조회 API
     *
     * @param employeeId 직원 아이디
     * @return 직원 상세 정보
     */
    @Operation(summary = "직원 상세 조회", description = "아이디를 입력받아 상세하게 조회한다.")
    @GetMapping("/{employeeId}")
    public ResponseEntity<CommonResponse<EmployeeResponse>> searchDetailEmployee(@Parameter(name = "employeeId", description = "직원 아이디", required = true) @PathVariable("employeeId") Long employeeId) {
        EmployeeResponse response = employeeService.getEmployee(employeeId)
                .map(EmployeeResponse::from)
                .orElse(null);
        return ResponseEntity.ok(CommonResponse.of(response));
    }

    /**
     * 직원 수정 API
     *
     * @param employeeId 직원 아이디
     * @param request    수정 파라미터
     * @return 수정된 직원 정보
     */
    @Operation(summary = "직원 정보 수정", description = "직원 정보를 수정한다.")
    @PutMapping("/{employeeId}")
    public ResponseEntity<CommonResponse<EmployeeResponse>> updateEmployee(
            @Parameter(name = "employeeId", description = "직원 아이디", example = "200",required = true) @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeRequest request
    ) {
        EmployeeData employeeData = employeeService.updateEmployee(employeeId, request.getJobId(), request.getManagerId(), request.getDepartmentId(), request.toDto());
        return ResponseEntity.ok(CommonResponse.of(EmployeeResponse.from(employeeData)));
    }
}
