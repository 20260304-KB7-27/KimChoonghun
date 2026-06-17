package org.scoula.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberVO {
    private Long no;
    private String username;
    private String password;
    private String email;
    private Integer birthYear;
    private Date regDate;
    private Date updatedDate;
}