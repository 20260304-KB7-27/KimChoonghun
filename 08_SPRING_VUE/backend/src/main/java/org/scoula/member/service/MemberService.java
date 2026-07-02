package org.scoula.member.service;

import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {

    // 회원 pk 중복검사
    Boolean checkDuplicate(String username);

    @Transactional
    MemberDTO join(MemberJoinDTO member);
}
