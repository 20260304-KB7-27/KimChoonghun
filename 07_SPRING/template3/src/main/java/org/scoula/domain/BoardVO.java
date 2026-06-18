package org.scoula.domain;

import lombok.*;

import java.util.Date;

@Data // GETTER, SETTER, ToString, EqualsAndHashCode, RequiredArgsConstructor
@NoArgsConstructor  // 🟢 핵심: MyBatis가 빈 객체를 생성할 수 있도록 기본 생성자 추가
@AllArgsConstructor // 🟢 @Builder를 안전하게 사용하기 위해 필수
@Builder
public class BoardVO {

    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
