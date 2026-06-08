package org.scoula.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter/Setter, ToString 등을 위해
@Builder // 직관적인 객체 생성을 위해
/*
    // @Builder가 있으면 이렇게 가독성 좋게 객체를 생성할 수 있습니다.
    User user = User.builder()
                .name("홍길동")
                .age(25)
                .email("hong@example.com")
                .build();
 */
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 만듦, 역직렬화(JSON -> Java 객체 변환)를 위해
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦, @Builder가 정상 작동하기 위해 (Builder는 전체 생성자를 필요로 한다.)
public class TravelImageVO {
    private Long no;
    private String filename;
    private Long travelNo;
}