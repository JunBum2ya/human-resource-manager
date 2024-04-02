package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.ManagerData;
import lombok.Builder;

@Builder
public record ManagerResponse(Long memberId, String firstName, String lastName, String email) {
    public static ManagerResponse from(ManagerData managerData) {
        if(managerData == null) {
            return null;
        }
        return ManagerResponse.builder()
                .memberId(managerData.memberId())
                .firstName(managerData.firstName())
                .lastName(managerData.lastName())
                .email(managerData.email())
                .build();
    }
}
