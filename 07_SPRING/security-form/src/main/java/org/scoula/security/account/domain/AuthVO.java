package org.scoula.security.account.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthVO implements GrantedAuthority {

    private String username;
    private String auth;

    // Spring Security
    @Override
    public String getAuthority() {
        return auth;
    }
}
