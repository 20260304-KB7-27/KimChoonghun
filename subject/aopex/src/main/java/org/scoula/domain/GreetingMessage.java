package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreetingMessage {
    private String name; // 입장한 사용자 이름
    private String content; // 채팅 내용
}
