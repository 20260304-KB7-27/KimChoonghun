package org.example.demo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="MyServlet", urlPatterns={"/xxx", "/yyy" })
public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("HelloServlet 요청");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Hello Servlet</h1>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy 호출");
    }
    @Override
    public void init() throws ServletException {
        System.out.println("init호출");
    }
}