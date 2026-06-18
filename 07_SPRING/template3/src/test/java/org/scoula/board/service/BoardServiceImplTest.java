package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.scoula.domain.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={RootConfig.class})
@Log4j2
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    void getList() {
        for(BoardDTO board: boardService.getList()) {
            log.info(board);
        }
    }

    @Test
    void get() {
        Long testNo = 1L;
        BoardDTO board = boardService.get(testNo);

        assertNotNull(board); // 조회결과 객체가 null이 아닌지
        assertEquals(testNo, board.getNo()); // 번호가 일치하는지

        log.info("조회된 게시글 : {}", board);
    }

    @Test
    void get2() {
        Long testNo = 99L;
        BoardDTO board = boardService.get(testNo);

        assertNotNull(board); // 조회결과 객체가 null이 아닌지
        assertEquals(testNo, board.getNo()); // 번호가 일치하는지

        log.info("조회된 게시글 : {}", board);
    }

    @Test
    void create() {
        BoardDTO board = BoardDTO.builder()
                .title("새로운 게시글 제목")
                .content("새로운 게시글 내용")
                .writer("testUser")
                .build();

        boardService.create(board);
    }

    @Test
    public void update() {
        Long targetNo = 1L;

        // 2. 수정할 데이터를 DTO에 담아줍니다.
        BoardDTO board = BoardDTO.builder()
                .no(targetNo) // 어느 글을 수정할지 번호(PK)가 반드시 있어야 합니다!
                .title("수정된 테스트 제목입니다")
                .content("수정된 테스트 내용입니다")
                .writer("updateUser")
                .build();

        // 3. update 메서드 실행 및 결과 체크
        boolean result = boardService.update(board);

        // 4. 결과가 true인지(즉, 반환값이 1이었는지) 검증합니다.
        assertTrue(result);
    }

    @Test
    public void delete() {
        Long targetNo = 1L;

        // 3. update 메서드 실행 및 결과 체크
        boolean result = boardService.delete(targetNo);

        // 4. 결과가 true인지(즉, 반환값이 1이었는지) 검증합니다.
        assertTrue(result);
    }
}