package com.ecorich.hrservice.service;

import com.ecorich.hrservice.domain.*;
import com.ecorich.hrservice.dto.LocationDetailData;
import com.ecorich.hrservice.dto.param.LocationSearchParam;
import com.ecorich.hrservice.repository.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 위치")
@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @InjectMocks
    private LocationService sut;

    @Mock
    private LocationRepository locationRepository;

    @DisplayName("파라미터와 페이지네이션으로 위치를 검색하면 위치 데이터가 페이징되어 반환된다.")
    @Test
    public void givenParameterAndPageable_whenSearchLocation_thenReturnsLocationDataPage() {
        //given
        LocationSearchParam param = LocationSearchParam.builder()
                .regionId(1L)
                .locationId(2L)
                .city("TEST")
                .build();
        Pageable pageable = Pageable.ofSize(10);
        given(locationRepository.searchLocation(any(LocationSearchParam.class),any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(createLocation(1L)),pageable,1L));
        //when
        Page<LocationDetailData> page = sut.searchLocation(param, pageable);
        //then
        assertThat(page).isNotEmpty();
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page).hasSize(1);
        then(locationRepository).should().searchLocation(any(LocationSearchParam.class),any(Pageable.class));
    }

    private Location createLocation(long locationId) {
        return Location.builder()
                .id(locationId)
                .streetAddress("서울시")
                .postalCode("1334-34")
                .city("SEOUL")
                .stateProvince("seoul")
                .country(Country.builder()
                        .id("KR")
                        .name("대한민국")
                        .region(Region.builder()
                                .id(3L)
                                .name("ASIA")
                                .build())
                        .build())
                .build();
    }

}
