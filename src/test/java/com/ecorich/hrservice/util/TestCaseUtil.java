package com.ecorich.hrservice.util;

import com.ecorich.hrservice.domain.*;

import java.time.LocalDate;

public class TestCaseUtil {
    public static Region createRegion() {
        return Region.builder()
                .id(1L)
                .name("ASIA")
                .build();
    }

    public static Country createCountry(Region region) {
        return Country.builder()
                .id("KR")
                .name("대한민국")
                .region(region)
                .build();
    }

    public static Location createLocation(Country country) {
        return Location.builder()
                .country(country)
                .streetAddress("AI STREET")
                .stateProvince("AI")
                .postalCode("16543")
                .city("COMPUTER")
                .build();
    }

    public static Employee createManager(Long employeeId,Job job) {
        return Employee.builder()
                .id(employeeId)
                .firstName("test")
                .lastName("test")
                .email("test@test.com")
                .hireDate(LocalDate.now())
                .salary(430.0)
                .job(job)
                .build();
    }

    public static Department createDepartment(Long departmentId, Location location) {
        return Department.builder()
                .id(departmentId)
                .name("HR")
                .location(location)
                .build();
    }

    public static Job createJob(String jobId) {
        return Job.builder()
                .id(jobId)
                .title("new job")
                .maxSalary(40000L)
                .minSalary(20000L)
                .build();
    }
}
