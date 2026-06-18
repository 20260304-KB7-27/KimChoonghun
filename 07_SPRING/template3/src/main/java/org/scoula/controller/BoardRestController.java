package org.scoula.controller;

import org.scoula.board.dto.BoardDTO; // DTO 사용을 위해 임포트 변경
import org.scoula.board.service.BoardService; // 🟢 올바른 서비스 패키지 경로로 수정!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardRestController {

    private final BoardService boardService; // final을 붙여 안정성 확보 권장

    @Autowired
    public BoardRestController(BoardService service) {
        this.boardService = service;
    }

    @GetMapping("/api/mapper/board")
    public List<BoardDTO> getBoardList() { // VO 대신 DTO 반환으로 변경
        return boardService.getList(); // 🟢 인터페이스에 정의된 올바른 메서드 호출!
    }
}