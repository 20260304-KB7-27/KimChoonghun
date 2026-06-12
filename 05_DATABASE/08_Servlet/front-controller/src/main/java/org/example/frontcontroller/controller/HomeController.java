package org.example.frontcontroller.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class HomeController extends HttpServlet {
    // Service
    public String getIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // service.###()
        // 비즈니스 로직 작성
        return "index";
    }
}
