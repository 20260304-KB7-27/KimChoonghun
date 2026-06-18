package org.scoula.board.service;

import org.scoula.board.dto.BoardDTO;
// import org.springframework.stereotype.Service; // 삭제
// import org.springframework.context.annotation.Bean; // 불필요하므로 삭제

import java.util.List;

public interface BoardService {

    public List<BoardDTO> getList(); // 인터페이스는 기본적으로 public이므로 생략 가능합니다.

    public BoardDTO get(Long no);

    public void create(BoardDTO board);

    public boolean update(BoardDTO board);

    public boolean delete(Long no);
}