package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.domain.Location;
import com.ecorich.hrservice.dto.param.LocationSearchParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - 위치")
@SpringBootTest
public class LocationRepositoryTest {

    @Autowired
    public LocationRepository locationRepository;

    @DisplayName("파라미터와 페이지네이션으로 위치를 조회하면 위치가 페이징되어 반환된다.")
    @Test
    public void givenParameterAndPagination_whenSelectLocation_thenReturnsLocationPage() {
        //given
        LocationSearchParam param = LocationSearchParam.builder()
                .countryId("UK")
                .build();
        Pageable pageable = Pageable.ofSize(10);
        //when
        Page<Location> page = locationRepository.searchLocation(param,pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getContent()).hasSizeGreaterThan(0);
    }

}
