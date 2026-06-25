package org.scoula.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.security.web.csrf.CsrfFilter;


@Configuration
@EnableWebSecurity  // Spring Security 활성화
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF 필터 앞에 encodingFilter을 놓겠다
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
        // URL별 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/alle")
                .permitAll()
                .antMatchers("/security/admin")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')");

        // form 기반 로그인 활성화
        http.formLogin()
                .loginPage("/security/login") // 로그인 페이지 커스텀
                .loginProcessingUrl("/security/login") // 스프링 기본제공 post 요청시 로그인 시도
                .defaultSuccessUrl("/");

        http.logout()
                .logoutUrl("/security/logout") // POST 요청을보내면 로그아웃 시도
                .invalidateHttpSession(true)
                .deleteCookies("JSESSION-ID") // 삭제할 쿠키
                .logoutSuccessUrl("/security/logout"); // 로그아웃 성공 시 이동할 페이지
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 관리자 계정
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$t5oZbmRtWxsx9vhU2kzkGuIqA8xn3vub4v0FcY9BnsS0D8XTyQxWm")
                .roles("ADMIN", "MEMBER");

        //멤버 계정
        auth.inMemoryAuthentication()
                .withUser("member")
                .password("1234")
                .roles("MEMBER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}