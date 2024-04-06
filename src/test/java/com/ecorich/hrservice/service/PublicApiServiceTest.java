package com.ecorich.hrservice.service;

import com.ecorich.hrservice.component.ApiConnector;
import com.ecorich.hrservice.dto.PetAttractItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 공공데이터")
@SpringBootTest
@ActiveProfiles("testdb")
public class PublicApiServiceTest {

    @Autowired
    private PublicApiService publicApiService;

    @MockBean
    private ApiConnector apiConnector;

    private final String sampleData = "{\"response\":{" +
            "\"header\":{\"resultCode\":\"0000\",\"resultMsg\":\"OK\"}," +
            "\"body\":{\"items\":{\"item\":[" +
            "{\"title\":\"헬로독하이캣\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 월~토 10:30~21:00 | 휴무일 : 매주 일요일 | 주차가능 | 반려동물 동반가능 | 반려동물 제한사항 : 제한사항 없음\",\"tel\":\"0507-1309-9590\",\"url\":null,\"address\":\"(08839) 서울특별시 관악구 신림로 108\",\"coordinates\":\"N37.4711305, E126.937598\",\"charge\":\"입장(이용료)가격 정보 : 변동 | 애견 동반 추가 요금 : 없음\"}" +
            ",{\"title\":\"다이소 창원상남2호점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 연중무휴 | 주차 불가 | 반려동물 동반불가\",\"tel\":\"055-267-6016\",\"url\":\"http://www.daiso.co.kr/\",\"address\":\"(51495) 경상남도 창원시 성산구 마디미로4번길 1\",\"coordinates\":\"N35.22374049, E128.685333\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 퇴계원점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 설, 추석 당일 휴무 | 주차가능 | 반려동물 동반불가\",\"tel\":\"031-527-2066\",\"url\":\"http://www.daiso.co.kr/\",\"address\":\"(12119) 경기도 남양주시 퇴계원읍 퇴계원로 54\",\"coordinates\":\"N37.65156518, E127.142407\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 파주교하점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 연중무휴 | 주차가능 | 반려동물 동반불가\",\"tel\":\"031-945-6015\",\"url\":\"http://www.daiso.co.kr/\",\"address\":\"(10874) 경기도 파주시 청석로 260\",\"coordinates\":\"N37.725103, E126.717738\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 파주목동동점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 연중무휴 | 주차가능 | 반려동물 동반불가\",\"tel\":\"031-947-6016\",\"url\":\"http://www.daiso.co.kr/\",\"address\":\"(10892) 경기도 파주시 청암로17번길 17\",\"coordinates\":\"N37.7295686, E126.73676\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 창원상남본점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 연중무휴 | 주차가능 | 반려동물 동반불가\",\"tel\":\"055-266-6016\",\"url\":\"http://www.daiso.co.kr/\",\"address\":\"(51496) 경상남도 창원시 성산구 상남로 88\",\"coordinates\":\"N35.22125836, E128.681237\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 파주문산점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 연중무휴 | 주차 불가 | 반려동물 동반불가\",\"tel\":\"031-954-6016\",\"url\":\"http://www.daiso.co.kr/\",\"category3\":null,\"address\":\"(10822) 경기도 파주시 문산읍 문향로67번길 1\",\"coordinates\":\"N37.85786156, E126.784859\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 파주봉일천점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 월~토 09:30~22:00, 일 10:00~22:00 | 휴무일 : 연중무휴 | 주차 불가 | 반려동물 동반불가\",\"tel\":\"031-949-0626\",\"url\":\"https://www.daiso.co.kr/\",\"address\":\"(10937) 경기도 파주시 조리읍 봉천로 39-1\",\"coordinates\":\"N37.74355143, E126.807284\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 파주운정1호점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 연중무휴 | 주차가능 | 반려동물 동반불가\",\"tel\":\"031-943-6005\",\"url\":\"http://www.daiso.co.kr/\",\"address\":\"(10895) 경기도 파주시 미래로 624\",\"coordinates\":\"N37.73295598, E126.750819\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}," +
            "{\"title\":\"다이소 창원중동점\",\"issuedDate\":\"11/30/22\",\"category1\":\"반려동물 서비스\",\"category2\":\"반려동물용품\",\"category3\":null,\"description\":\"운영시간 : 매일 10:00~22:00 | 휴무일 : 연중무휴 | 주차가능 | 반려동물 동반불가\",\"tel\":\"055-255-6016\",\"url\":\"http://www.daiso.co.kr/\",\"address\":\"(51380) 경상남도 창원시 의창구 중동중앙로 83\",\"coordinates\":\"N35.25927237, E128.627532\",\"charge\":\"입장(이용료)가격 정보 : 없음 | 애견 동반 추가 요금 : 없음\"}]}" +
            ",\"numOfRows\":\"10\",\"pageNo\":\"1\",\"totalCount\":\"23929\"}}}";

    @DisplayName("파라미터로 펫 동반 시설을 요청하면 페이징되어 호출된다.")
    @Test
    public void givenParameter_whenRequestPetAttractData_thenReturnsPetAttractItemPage() {
        //given
        given(apiConnector.connect(any(String.class))).willReturn(sampleData);
        //when
        Page<PetAttractItem> page = publicApiService.searchPetAttract(1, 10, null);
        //then
        then(apiConnector).should().connect(any(String.class));
        assertThat(page).isNotNull();
        assertThat(page.getSize()).isEqualTo(10);
    }

}
