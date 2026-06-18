package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.domain.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor // final, @notNull이 붙은 필드만 포함하는 생성자 생성
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getList() {
        return List.of();
    }

    @Override
    public BoardDTO get(Long no) {
        BoardVO vo = boardMapper.get(no);
        // dto가 null이면 예외처리
        return Optional.ofNullable(BoardDTO.of(vo))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void create(BoardDTO board) {
        // UserDetail 정보를 가져와서 Board에 같이 외래키로 작성해줌
        BoardVO boardVO = board.toVo();
        boardMapper.create(boardVO);
        board.setNo(boardVO.getNo());
    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);
        return boardMapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);
        return boardMapper.delete(no) == 1;
    }
}
