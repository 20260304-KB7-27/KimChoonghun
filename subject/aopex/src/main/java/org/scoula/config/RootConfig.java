package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
        "org.scoula.chat.service",
        "org.scoula.advice", // Aspect 공통 관심사 모듈
        "org.scoula.sample.service", // Target : AOP 적용 대상
})
@EnableAspectJAutoProxy // Aspect 프록시 생성 활성화 -> @Aspect 클래스를 인식해서 프록시를 생성한다.
public class RootConfig {

}
