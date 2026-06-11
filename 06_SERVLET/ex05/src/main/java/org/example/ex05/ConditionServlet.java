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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/jstl")
public class ConditionServlet extends HttpServlet {
    ServletContext sc;

    /*
       EL Scope

     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String scoreStr = req.getParameter("score");

        int score = 0;

        if (scoreStr != null && scoreStr.trim().isEmpty()) {
            score = Integer.parseInt(scoreStr);
        }

        req.setAttribute("score", score);

        RequestDispatcher rd = req.getRequestDispatcher("jstl/condition.jsp");
        rd.forward(req, res);
    }
}