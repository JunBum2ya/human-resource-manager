package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.SimpleEmployeeData;
import lombok.Builder;

@Builder
public record ManagerResponse(Long memberId, String firstName, String lastName, String email) {
    public static ManagerResponse from(SimpleEmployeeData simpleEmployeeData) {
        if(simpleEmployeeData == null) {
            return null;
        }
        return ManagerResponse.builder()
                .memberId(simpleEmployeeData.memberId())
                .firstName(simpleEmployeeData.firstName())
                .lastName(simpleEmployeeData.lastName())
                .email(simpleEmployeeData.email())
                .build();
    }
}
