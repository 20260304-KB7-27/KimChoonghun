package org.scoula.security.account.domain;

import java.io.Serializable;

public interface GrantedAuthority extends Serializable {
    String getAuthority();
}
