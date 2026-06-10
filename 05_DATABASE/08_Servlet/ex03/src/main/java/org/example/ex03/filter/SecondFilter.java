package org.example.ex03.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*
    Filter
    - 클라이언트 요청이 Servlet/JSP에 도착하기 전 / 응답이 나가기 전에 공통 처리를 할 수 있는 컴포넌트
*/
@WebFilter(urlPatterns = {"/*"}) // 모든 동작에 대한 필터
public class SecondFilter implements Filter {

    // filter가 생성될때 동작
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // body 한글깨짐 방지
        servletRequest.setCharacterEncoding("UTF-8");

        // 클라이언트 요청이 들어왔을때 (Servlet 호출 전 전처리)
        System.out.println("필터2 동작 시작");
        filterChain.doFilter(servletRequest, servletResponse); // 다음 필터 또는 servlet으로 요청 전달

        // 클라이언트 응답하기 전 (후처리)
        System.out.println("필터2 동작 끝");

    }

    // 서버가 종료될때 호출
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
