package org.scoula.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
public class MemberMapperTest {

    @Autowired
    private MemberMapper mapper;

    @Test
    @DisplayName("1. 회원 가입 (Insert) 테스트 - Setter 사용")
    public void testInsert() {
        // 기본 생성자로 객체 생성 후 Setter로 값 주입
        MemberVO member = new MemberVO();
        member.setUsername("테스트유저");
        member.setPassword("123456");
        member.setEmail("test@scoula.org");
        member.setBirthYear(1999);

        int result = mapper.insert(member);

        log.info("▶️ 삽입된 행의 개수: {}", result);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("2. 전체 회원 조회 (List) 테스트")
    public void testGetList() {
        List<MemberVO> list = mapper.getList();

        log.info("▶️ 전체 회원 목록 ◀️");
        for (MemberVO member : list) {
            log.info(member);
        }

        assertNotNull(list); // 리스트가 null이 아니어야 테스트 성공
    }

    @Test
    @DisplayName("3. 단일 회원 조회 (Read) 테스트")
    public void testGet() {
        // DB에 존재하는 실제 PK(no) 번호를 입력해야 합니다. (예: 1L)
        // 위 testInsert() 실행 후 DB를 확인하고 번호를 바꿔서 테스트해 보세요.
        Long targetNo = 1L;

        MemberVO member = mapper.get(targetNo);
        log.info("▶️ {}번 회원 정보: {}", targetNo, member);
    }

    @Test
    @DisplayName("4. 회원 정보 수정 (Update) 테스트 - Setter 사용")
    public void testUpdate() {
        // 기본 생성자로 객체 생성 후 Setter로 값 주입
        MemberVO member = new MemberVO();
        member.setNo(1L); // DB에 존재하는 실제 번호
        member.setUsername("수정된유저");
        member.setPassword("654321");
        member.setEmail("updated@scoula.org");
        member.setBirthYear(2002);

        int result = mapper.update(member);

        log.info("▶️ 수정된 행의 개수: {}", result);
    }

    @Test
    @DisplayName("5. 회원 삭제 (Delete) 테스트")
    public void testDelete() {
        // DB에 존재하는 실제 PK(no) 번호로 셋팅
        Long targetNo = 1L;

        int result = mapper.delete(targetNo);

        log.info("▶️ 삭제된 행의 개수: {}", result);
    }
}