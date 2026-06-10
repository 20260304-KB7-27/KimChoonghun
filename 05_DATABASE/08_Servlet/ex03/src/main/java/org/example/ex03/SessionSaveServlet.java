package org.example.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/*
    Session
    - 서버 쪽에 저장됨 (JSESSIONID -> 식별표)
    - 객체도 통째로 저장이 가능하다.
    - 일정시간동안 활동이 없으면 만료 또는 invalidate()로 즉시 삭제 가능
 */
@WebServlet(name = "session-save", value = "/session-save")
public class SessionSaveServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MIME 타입 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String value = request.getParameter("sessionValue");

        // getSession() : 세션이 업승면 새로 생성 (JSESSIONID 쿠키 발급)
        HttpSession session = request.getSession();

        session.setAttribute("mySessionData", value);

        // 마지막 요청후 지정 시간동안 요청이 없으면 세션 소멸
        session.setMaxInactiveInterval(10);

        // 자바 I/O
        PrintWriter out = response.getWriter();

        // html 작성
        out.println("<html><body>");
        out.println("<h2>Session 저장 결과</h2>");
        out.println("<h2>저장된 값 :"+ session.getAttribute("mySessionData")+"</h2>");
        out.println("<a href='session_cookie.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}