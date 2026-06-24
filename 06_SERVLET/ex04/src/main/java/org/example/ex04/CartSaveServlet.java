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

@WebServlet(name = "CartSaveServlet", value = "/cart_save")
public class CartSaveServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");


        String product = request.getParameter("product");
        HttpSession session = request.getSession();

        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<String>();
            session.setAttribute("cart", cart);
        }

        if (product != null && !product.trim().isEmpty()) {
            cart.add(product);
        }

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>장바구니 저장</title></head>");
        out.println("<body>");

        if (product != null) {
            out.println("<h3>" + product + " 추가!!</h3>");
        } else {
            out.println("<h3>선택된 상품이 없습니다.</h3>");
        }
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a><br>");
        out.println("<a href='cart_view'>장바구니 보기</a>");

        out.println("</body></html>");
    }

    public void destroy() {
    }
}