package org.example.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "board", value = "/board")
public class BoardServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> enu = request.getParameterNames();

        /// 자바 I/O
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        while(enu.hasMoreElements()) {
            // parameter key 목록에서 1개만 꺼냄
            String name = enu.nextElement();

            String value = request.getParameter(name);
            out.print(name+" : "+ value);
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}