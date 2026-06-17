package org.scoula.mapper;

import org.scoula.domain.MemberVO;
import java.util.List;

public interface MemberMapper {
    // 1. 회원 가입 (Create)
    int insert(MemberVO member);

    // 2. 전체 회원 조회 (Read - List)
    List<MemberVO> getList();

    // 3. 단일 회원 조회 (Read - Single)
    MemberVO get(Long no);

    // 4. 회원 정보 수정 (Update)
    int update(MemberVO member);

    // 5. 회원 탈퇴 (Delete)
    int delete(Long no);
}