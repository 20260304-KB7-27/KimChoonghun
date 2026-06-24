package org.example.ex04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CartDeleteServlet", value = "/cart_delete")
public class CartDeleteServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        session.invalidate();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.print("장바구니 비웠음!!<a href='session_product.jsp'>상품 선택 페이지</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}