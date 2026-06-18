package org.scoula.service;

import org.scoula.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BoardService {

    private BoardMapper mapper;

    @Autowired // DI
    public BoardService(BoardMapper mapper) {
        this.mapper = mapper;
    }
//
//    @GetMapping("/api/mapper/board")
//    public List<BoardVO> getAllBaordByMapper() {
//        // 전처리
//        List<BoardVO> boardVOS = mapper.selectAllByMapper();
//
//        return boardVOS;
//    }
//
//    @GetMapping("/api/annotation/board")
//    public List<BoardVO> getAllByAnnotation() {
//        // 전처리
//        List<BoardVO> boardVOS = mapper.selectAllByAnnotation();
//
//        return boardVOS;
//    }
}

