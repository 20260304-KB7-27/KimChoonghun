package org.scoula.member.mapper;

import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {

    // username 중복 체크할 때 사용
    MemberVO findByUsername(String username);

    // 단건 조회
    MemberVO get(String username);

    // 회원 정보 추가
    int insert(MemberVO member);

    // 회원 권한 정보 추가
    int insertAuth(AuthVO auth);
}
