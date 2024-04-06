package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.Department;
import com.ecorich.hrservice.domain.Employee;
import com.ecorich.hrservice.domain.Job;
import com.ecorich.hrservice.domain.constant.ResultStatus;
import com.ecorich.hrservice.dto.EmployeeData;
import com.ecorich.hrservice.dto.EmployeeDetailData;
import com.ecorich.hrservice.exception.CustomException;
import com.ecorich.hrservice.repository.DepartmentRepository;
import com.ecorich.hrservice.repository.EmployeeRepository;
import com.ecorich.hrservice.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * 직원 상세 조회 메소드
     *
     * @param employeeId
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<EmployeeDetailData> getEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.map(EmployeeDetailData::from);
    }

    /**
     * 직원 수정 메소드
     *
     * @param employeeId
     * @param employeeData
     * @return
     * @throws CustomException
     */
    public EmployeeDetailData updateEmployee(Long employeeId, String jobId, Long managerId, Long departmentId, EmployeeData employeeData) throws CustomException {
        try {
            Employee employee = employeeRepository.getReferenceById(employeeId);
            Job job = jobRepository.getReferenceById(jobId);
            Employee manager = employeeRepository.getReferenceById(managerId);
            Department department = departmentId != null ? departmentRepository.getReferenceById(departmentId) : null;
            employee.update(employeeData.firstName(), employeeData.lastName(), employeeData.email(), employeeData.phoneNumber(), employeeData.hireDate(), job, employeeData.salary(), employeeData.commissionPct(), manager, department);
            return EmployeeDetailData.from(employee);
        } catch (EntityNotFoundException e) {
            log.warn("해당하는 직원이 없습니다. - employeeId : {}", employeeId);
            throw new CustomException(ResultStatus.ACCESS_NOT_EXIST_ENTITY);
        }
    }
}
