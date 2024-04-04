package com.ecorich.hrservice.dto.response;

import com.ecorich.hrservice.dto.PetAttractItem;
import com.ecorich.hrservice.dto.PetAttractResult;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Builder
public record PetAttractResponse(
        String title,
        String issuedDate,
        List<String> category,
        List<String> description,
        String telephone,
        String url,
        String address,
        String coordinates,
        String charge
) {
    public static PetAttractResponse from(PetAttractItem item) {
        List<String> categories = new LinkedList<>();
        if(item.getCategory1() != null) {
            categories.add(item.getCategory1());
        }
        if(item.getCategory2() != null) {
            categories.add(item.getCategory2());
        }
        if(item.getCategory3() != null) {
            categories.add(item.getCategory3());
        }
        return PetAttractResponse.builder()
                .title(item.getTitle())
                .issuedDate(item.getIssuedDate())
                .category(categories)
                .description(Arrays.stream(item.getDescription().split("[|]")).toList())
                .telephone(item.getTel())
                .url(item.getUrl())
                .address(item.getAddress())
                .coordinates(item.getCoordinates())
                .charge(item.getCharge())
                .build();
    }
}
