package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.dto.request.EmployeeRequest;
import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.EmployeeResponse;
import com.ecorich.hrservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/employee")
@RequiredArgsConstructor
@RestController
public class EmployeeRestController {

    private final EmployeeService employeeService;

    /**
     * 단일 직원 조회 메소드
     *
     * @param employeeId
     * @return
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<CommonResponse<EmployeeResponse>> searchDetailEmployee(@PathVariable("employeeId") Long employeeId) {
        EmployeeResponse response = employeeService.getEmployee(employeeId)
                .map(EmployeeResponse::from)
                .orElse(null);
        return ResponseEntity.ok(CommonResponse.of(response));
    }

    /**
     * 직원 수정 API
     * @param employeeId
     * @param request
     * @return
     */
    @PutMapping("/{employeeId}")
    public ResponseEntity<CommonResponse<EmployeeResponse>> updateEmployee(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeRequest request
    ) {
        EmployeeData employeeData = employeeService.updateEmployee(employeeId, request.getJobId(), request.getManagerId(), request.getDepartmentId(), request.toDto());
        return ResponseEntity.ok(CommonResponse.of(EmployeeResponse.from(employeeData)));
    }
}
