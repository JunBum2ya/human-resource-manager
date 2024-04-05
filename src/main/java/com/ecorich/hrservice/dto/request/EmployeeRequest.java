package com.ecorich.hrservice.dto.request;

import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.dto.JobData;
import com.ecorich.hrservice.dto.SimpleEmployeeData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 직원 수정 파라미터 포맷
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeRequest {
    @NotEmpty(message = "작업 아이디를 입력하세요.")
    private String jobId;
    @NotNull(message = "관리자 아이디를 입력하세요.")
    private Long managerId;
    private Long departmentId;
    private String firstName;
    @NotEmpty(message = "성씨를 입력하세요.")
    private String lastName;
    @NotEmpty(message = "email을 입력하세요.")
    @Email(message = "email 형식을 만족해야 합니다.")
    private String email;
    private String phoneNumber;
    @NotNull(message = "채용일자를 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    @NotNull(message = "월급을 입력하세요.")
    private Double salary;
    private Double commissionPct;

    public EmployeeData toDto() {
        return EmployeeData.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .hireDate(hireDate)
                .salary(salary)
                .commissionPct(commissionPct)
                .build();
    }
}
