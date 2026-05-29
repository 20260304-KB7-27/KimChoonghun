package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    @Test
    void add() {
        // given : 테스트에 필요한 준비
        Calculation calculation = new Calculation();

        // when : 테스트 시점
        int result = calculation.add(2,3);

        // then : 결과 확인
        assertEquals(5, result);
    }

    @Test
    void assertJExample() {
        String text = "Hello, AssertJ";
    }
}