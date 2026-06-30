package org.scoula.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.util.JwtProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 요청에 JWT가 담겨왔을때 검증하고
// securityContext에 유저 정보를 담아주는 필터
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // jwt가 담겨질 key (header)
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Bearer 토큰을 가진 사람한테 권한을 줄 것
    public static final String BEARER_PREFIX = "Bearer";

    private final JwtProcessor jwtProcessor; // 토큰 검증용
    private final UserDetailsService userDetailsService; // 사용자 조회용

    // 검증 메서드
    private Authentication getAuthentication(String token) {
        // 토큰에서 username 꺼내오고 sign 검증
        String username = jwtProcessor.getUsername(token);

        // UserDetail 객체 만들기 (데이터베이스에서 username으로 찾아서 유저 데이터 넣음)
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request -> jwt 꺼내기
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            // prefix 지우기 (Bearer 키워드 제거) -> 순수 JWT만 남긴다.
            String token = bearerToken.substring(BEARER_PREFIX.length());

            // 토큰 -> Authentication 객체로 변환후 SecurityContext에 저장
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        super.doFilter(request, response, filterChain); // 다음 필터로 넘기기
    }
}
