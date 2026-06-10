package org.example.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/*
    쿠키 (Cookie)
    - key-value 쌍으로 클라이언트(브라우저) 쪽에 저장됨
    - 만료시간 지정 가능, 정하지 않으면 브라우저가 닫힐때 사라진다.
    - 브라우저가 다음 요청을 보낼때부터 http 요청에 cookie를 담아 전송
 */
@WebServlet(name = "cookie-save", value = "/cookie-save")
public class CookieSaveServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MIME 타입 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String value = request.getParameter("cookieValue");

        // 쿠키 객체 생성
        Cookie cookie = new Cookie("myCookieData", value);

        // 쿠키 만료 시간 설정
        cookie.setMaxAge(10); // 10초 유지

        response.addCookie(cookie); // 브라우저로 전송

        // 자바 I/O
        PrintWriter out = response.getWriter();

        // html 작성
        out.println("<html><body>");
        out.println("<h2>Cookie 저장 결과</h2>");
        out.println("<h2>저장 요청된 값 :"+ value+"</h2>");
        out.println("<a href='session_cookie.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}