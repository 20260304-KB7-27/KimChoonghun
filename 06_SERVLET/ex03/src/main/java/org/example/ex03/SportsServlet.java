package org.example.ex03;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SportsServlet", value = "/sports")
public class SportsServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String[] sports = request.getParameterValues("sports");
        String gender = request.getParameter("sex");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (sports != null) {
            for (String s: sports) {
                out.println("<h1>좋아하는 운동: " + s + "</h1>");
            }
            out.println("</h1>");
        }
        out.println("<h1>성별: " + gender + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}