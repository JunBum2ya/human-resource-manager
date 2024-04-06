package com.ecorich.hrservice.dto.request;

import com.ecorich.hrservice.dto.DepartmentData;
import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.dto.JobData;
import com.ecorich.hrservice.dto.SimpleEmployeeData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 직원 수정 파라미터 포맷
 */
@Schema(name = "직원 정보 요청 데이터", description = "수정을 위한 직원 정보")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeRequest {
    @Schema(description = "작업 아이디", example = "AD_ASST")
    @NotEmpty(message = "작업 아이디를 입력하세요.")
    private String jobId;
    @Schema(description = "관리자 아이디", example = "101")
    @NotNull(message = "관리자 아이디를 입력하세요.")
    private Long managerId;
    @Schema(description = "부서 아이디", example = "10")
    private Long departmentId;
    @Schema(description = "이름", example = "first")
    private String firstName;
    @Schema(description = "성씨", example = "last")
    @NotEmpty(message = "성씨를 입력하세요.")
    private String lastName;
    @Schema(description = "이메일", example = "test@test.com")
    @NotEmpty(message = "email을 입력하세요.")
    @Email(message = "email 형식을 만족해야 합니다.")
    private String email;
    @Schema(description = "핸드폰 번호", example = "010-1234-5678")
    private String phoneNumber;
    @Schema(description = "채용일자", example = "2024-04-06")
    @NotNull(message = "채용일자를 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    @Schema(description = "월급", example = "40000")
    @NotNull(message = "월급을 입력하세요.")
    private Double salary;
    @Schema(description = "커미션 비율", example = "4.34")
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
