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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "CartViewServlet", value = "/cart_view")
public class CartViewServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20);
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>장바구니 보기</title></head>");
        out.println("<body>");
        out.println("<p>장바구니 리스트상품: "+cart+"</p>");
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a><br>");
        out.println("<a href='cart_delete'>장바구니 비우기</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}