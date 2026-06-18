package org.scoula.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.scoula.board.service.BoardService;
import org.scoula.domain.BoardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // MyBatis 매퍼임을 명시
@Repository
public interface BoardMapper {

    // 게시글 전체조회
    public List<BoardVO> getList();

    // 게시글 단건조회
    public BoardVO get(Long no);

    // 게시글 게시
    public void create(BoardVO vo);

    public int update(BoardVO vo);

    public int delete(Long no);
}
