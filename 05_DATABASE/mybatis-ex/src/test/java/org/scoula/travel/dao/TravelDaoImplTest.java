package org.scoula.travel.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.scoula.travel.domain.TravelVO;

import java.util.List;

class TravelDaoImplTest {

    TravelDaoImpl dao = new TravelDaoImpl();

    @Test
    void getTotalCount() {
        int count = dao.getTotalCount();

        System.out.println(count);

        Assertions.assertTrue(count > 0);
    }

    @Test
    void getTravels() {
        List<TravelVO> VOList = dao.getTravels(0,5);

        System.out.println(VOList);

        Assertions.assertNotNull(VOList);
    }

    @Test
    void getDistricts() {
        List<String> districts = dao.getDistricts();

        System.out.println(districts);

        Assertions.assertNotNull(districts);
    }

    @Test
    void getTravelsByDistrict() {
        List<TravelVO> VOList = dao.getTravelsByDistrict("수도권");

        System.out.println(VOList);

        Assertions.assertNotNull(VOList);
    }

    @Test
    void insert() {
        TravelVO travel = TravelVO.builder()
                .no(300L)
                .district("서울")
                .title("남산 서울타워")
                .description("서울의 대표적인 랜드마크입니다.")
                .address("서울특별시 용산구 남산공원길 105")
                .phone("02-3455-9277")
                .build();

        dao.insert(travel);
    }

    @Test
    void update() {
        TravelVO travel = TravelVO.builder()
                .no(300L) // 수정할 데이터의 ID (DB에 실제로 존재하는 ID여야 테스트가 성공합니다)
                .district("부산")
                .title("광안리 해수욕장")
                .description("광안대교 야경이 아름다운 곳")
                .address("부산 수영구 광안해변로 219")
                .phone("051-622-4251")
                .build();
        dao.update(travel);
    }

    @Test
    void remove() {
        dao.remove(300L);
    }
}