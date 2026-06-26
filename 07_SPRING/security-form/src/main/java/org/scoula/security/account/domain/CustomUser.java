package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
// 인증 성공하면 Authentication 객체의 principal로 저장된다
public class CustomUser extends User {

    private MemberVO memberVO;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO member){
        super(member.getUsername(), member.getPassword(), member.getAuthList());
        this.memberVO = member;
    }
}
