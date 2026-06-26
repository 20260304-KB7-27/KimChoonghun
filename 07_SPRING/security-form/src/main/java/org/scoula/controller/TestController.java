package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Log4j2
@RequestMapping("/member")
public class TestController {

    @GetMapping("/principal")
    public String print1(Principal principal) {
        return principal.getName();
    }

    // Principal
    @GetMapping("/2")
    public String print2(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication;
        return userDetails.getUsername();
    }

    // @AuthenicationPrincipal
    @GetMapping("/3")
    public String print3(@AuthenticationPrincipal CustomUser customUser) {
        MemberVO memberVO = customUser.getMemberVO();
        return memberVO.getUsername();
    }

    // Post 요청으로 오고 게시글 생성이다
    @GetMapping("/board")
    public MemberVO print4(@AuthenticationPrincipal CustomUser customUser) {
        MemberVO memberVO = customUser.getMemberVO();
//        service.createBoard(customUser, body);
        return memberVO;
    }
}
