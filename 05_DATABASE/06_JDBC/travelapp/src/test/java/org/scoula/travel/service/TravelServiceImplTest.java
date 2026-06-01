package org.scoula.travel.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.scoula.travel.dao.TravelDao;
import org.scoula.travel.domain.TravelVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
    단위 테스트
    - Mock으로 TravelDAO를 대체해서 서비스 로직만 검즘
 */
@ExtendWith(MockitoExtension.class)
class TravelServiceImplTest {

    @Mock TravelDao mockDao;

    // @RequiredArgsConstructor 생성자로 mockDao 자동 주입
    @InjectMocks TravelServiceImpl service;

    @Test
    void printTravels() {
        // given -> 결과물로 나왔을때 List(TravelNo)
        TravelVO travel = TravelVO.builder()
                .no(1L)
                .district("서울")
                .title("경복궁")
                .build();

        // when
        // -> dao.getTravels()를 호출하면 List.of(travel)를 반환해줘
        when(mockDao.getTravels()).thenReturn(List.of(travel));

        // service의 printTravels 메소드를 호출했을대
        service.printTravels();

        // then
        // 호출되었는지
        verify(mockDao).getTravels();
    }
}