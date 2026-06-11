package org.example.ex05;

import org.example.ex05.domain.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jstl2")
public class ForEachServlet extends HttpServlet {
    ServletContext sc;
    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        List<Member> members = new ArrayList<>();

        members.add(new Member("홍길동", "hong"));
        members.add(new Member("김철수", "kkim"));
        members.add(new Member("이영희", "ee20"));

        req.setAttribute("memberList", members);

        req.getRequestDispatcher("jstl/forEach.jsp").forward(req, res);
    }
}