package com.ecorich.hrservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
public class PetAttractRequest {
    private Integer page;
    private Integer size;
    private String keyword;

    public int page() {
        return page != null ? page : 1;
    }

    public int size() {
        return size != null ? size : 10;
    }

    public String keyword() {
        return StringUtils.hasText(keyword) ? keyword : null;
    }

}
