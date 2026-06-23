package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @Controller
    @ResponseBody -> body 영역에 값을 직접 작성하겠다
 */
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor // -> final / not null로 된 필드를 포함하는 생성자를 만들어준다.
public class BoardController {
    private final BoardService service;

    // 전체조회
    @GetMapping("/list")
    public List<BoardDTO> getList() {
        return service.getList();
    }

    // ResponseEntity : Header, Body에 원하는 내용을 작성하고 싶을때
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList2() {

        return ResponseEntity.ok(service.getList());
    }

    // 단건 조회 api/board/{no}
    @GetMapping("{no}")
    public ResponseEntity<BoardDTO> getById(@PathVariable Long no) {
        return ResponseEntity.ok(service.get(no));
    }

    // [POST] /api/board
    @PostMapping("")
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO boardDTO) {
        // 201 -> 새로운 데이터가 입력되었다.
//        return ResponseEntity.ok(service.create(boardDTO));
//        return ResponseEntity.created(URI.create("/board/"+boardDTO.getNo()))
//                .body(service.create(boardDTO));
        // 1. 서비스 호출 (DB에 데이터 저장)
        service.create(boardDTO);
        // 2. 저장되면서 글 번호(no) 등이 채워진 DTO를 그대로 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(boardDTO);
    }

    // 수정요청 [PUT] /api/board/{no}
    @PutMapping("/{no}")
    public ResponseEntity<Boolean> update(@PathVariable Long no, @RequestBody BoardDTO boardDTO) {
        return ResponseEntity.ok(service.update(boardDTO));
    }

    // 삭제요청 [DELETE] /api/board/{no}
    @DeleteMapping("/{no}")
    public ResponseEntity<Boolean> delete(@PathVariable Long no) {
        return ResponseEntity.ok(service.delete(no));
    }
}
