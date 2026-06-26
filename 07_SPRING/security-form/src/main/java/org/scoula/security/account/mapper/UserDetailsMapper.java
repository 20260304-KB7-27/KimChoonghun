package org.scoula.security.account.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.account.domain.MemberVO;

@MapperScan(basePackages = {"org.scolua.security.account.mapper"})
public interface UserDetailsMapper {
    MemberVO get(String username);
}
