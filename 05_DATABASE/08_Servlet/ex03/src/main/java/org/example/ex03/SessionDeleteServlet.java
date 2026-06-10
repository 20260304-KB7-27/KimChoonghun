package org.example.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/*
    Session
    - 서버 쪽에 저장됨 (JSESSIONID -> 식별표)
    - 객체도 통째로 저장이 가능하다.
    - 일정시간동안 활동이 없으면 만료 또는 invalidate()로 즉시 삭제 가능
 */
@WebServlet(name = "session-delete", value = "/session-delete")
public class SessionDeleteServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MIME 타입 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // getSession() : 세션이 업승면 새로 생성 (JSESSIONID 쿠키 발급)
        HttpSession session = request.getSession();

        // 세션에서 키에 해당하는 데이터 삭제
        session.removeAttribute("mySessionData");

        // 세션 전체 삭제 -
        // session.invalidate();

        // 자바 I/O
        PrintWriter out = response.getWriter();

        // html 작성
        out.println("<html><body>");
        out.println("삭제완료");
        out.println("<a href='session_cookie.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}