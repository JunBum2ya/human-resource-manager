package com.ecorich.hrservice.repository;

import com.ecorich.hrservice.config.JpaConfig;
import com.ecorich.hrservice.domain.Country;
import com.ecorich.hrservice.domain.Location;
import com.ecorich.hrservice.domain.Region;
import com.ecorich.hrservice.dto.param.LocationSearchParam;
import com.ecorich.hrservice.util.TestCaseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리포지토리 테스트 - 위치")
@Import(JpaConfig.class)
@DataJpaTest
@ActiveProfiles("testdb")
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    public void initData() {
        Region region = TestCaseUtil.createRegion();
        Country country = TestCaseUtil.createCountry(region);
        Location location = locationRepository.save(TestCaseUtil.createLocation(country));
    }

    @DisplayName("파라미터와 페이지네이션으로 위치를 조회하면 위치가 페이징되어 반환된다.")
    @Test
    public void givenParameterAndPagination_whenSelectLocation_thenReturnsLocationPage() {
        //given
        LocationSearchParam param = LocationSearchParam.builder()
                .countryId("KR")
                .build();
        Pageable pageable = Pageable.ofSize(10);
        //when
        Page<Location> page = locationRepository.searchLocation(param,pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getContent()).hasSizeGreaterThan(0);
    }

}
