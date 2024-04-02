package com.ecorich.hrservice.controller;

import com.ecorich.hrservice.dto.response.CommonResponse;
import com.ecorich.hrservice.dto.response.EmployeeResponse;
import com.ecorich.hrservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RequiredArgsConstructor
@RestController
public class EmployeeRestController {
    private final EmployeeService employeeService;

    /**
     * 단일 직원 조회 메소드
     * @param employeeId
     * @return
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<CommonResponse<EmployeeResponse>> currentMember(@PathVariable("employeeId") Long employeeId) {
        EmployeeResponse response = employeeService.getEmployee(employeeId)
                .map(EmployeeResponse::from)
                .orElse(null);
        return ResponseEntity.ok(CommonResponse.of(response));
    }
}
