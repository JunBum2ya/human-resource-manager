package com.ecorich.hrservice.component;

import com.ecorich.hrservice.domain.constant.ResultStatus;
import com.ecorich.hrservice.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ApiConnector {

    public String connect(String baseUrl) throws CustomException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> result = restTemplate.exchange(baseUrl,HttpMethod.GET,request,String.class);
            return result.getBody();
        }catch (Exception e) {
            throw new CustomException(ResultStatus.UNKNOWN_EXCEPTION);
        }
    }

}
