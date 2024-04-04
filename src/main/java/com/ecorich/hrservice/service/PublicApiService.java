package com.ecorich.hrservice.service;

import com.ecorich.hrservice.component.ApiConnector;
import com.ecorich.hrservice.dto.PetAttractItem;
import com.ecorich.hrservice.dto.PetAttractResult;
import com.ecorich.hrservice.domain.constant.ResultStatus;
import com.ecorich.hrservice.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublicApiService {

    @Value("${public-api.pet-attract.url}")
    private String petAttractUrl;
    @Value("${public-api.pet-attract.key}")
    private String petAttractKey;

    private final ApiConnector apiConnector;
    private final ObjectMapper objectMapper;

    public Page<PetAttractItem> searchPetAttract(int page, int size, String keyword) throws CustomException {
        String baseUrl = String.format("%s?serviceKey=%s&pageNo=%d&numOfRows=%d%s",petAttractUrl,petAttractKey,page,size,keyword != null ? "&keyword=" + keyword : "");
        String apiResult = apiConnector.connect(baseUrl);
        try {
            PetAttractResult result = objectMapper.readValue(apiResult, PetAttractResult.class);
            return new PageImpl<>(result.getItems(), PageRequest.of(page,size),result.getTotalCount());
        }catch (Exception e) {
            throw new CustomException(ResultStatus.UNKNOWN_EXCEPTION);
        }
    }

}
