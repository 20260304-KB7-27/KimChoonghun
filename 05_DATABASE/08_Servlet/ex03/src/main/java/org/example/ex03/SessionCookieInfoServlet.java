package org.example.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/*
    Session
    - 서버 쪽에 저장됨 (JSESSIONID -> 식별표)
    - 객체도 통째로 저장이 가능하다.
    - 일정시간동안 활동이 없으면 만료 또는 invalidate()로 즉시 삭제 가능
 */
@WebServlet("/info")
public class SessionCookieInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h2>Session / Cookie 현재 상태</h2>");

        out.println("<h3>Session</h3>");
        HttpSession session = req.getSession(false);

        if (session == null) {
            out.println("세션 없음<br>");
        } else {
            String sessionValue = (String) session.getAttribute("mySessionData");
            out.println("세션 ID: " + session.getId() + "<br>");
            out.println("mySessionData: " + (sessionValue != null ? sessionValue : "없음") + "<br>");
        }

        out.println("<h3>Cookie</h3>");

        // 쿠키들 꺼내기
        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            out.println("쿠키 없음<br>");
        } else {
            String cookieValue = null;
            for (Cookie c : cookies) {
                if ("myCookieData".equals(c.getName())) {
                    cookieValue = c.getValue();
                    break;
                }
            }
            out.println("myCookieData: " + (cookieValue != null ? cookieValue : "없음") + "<br>");
        }

        out.println("<br><a href='session_cookie.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }
}