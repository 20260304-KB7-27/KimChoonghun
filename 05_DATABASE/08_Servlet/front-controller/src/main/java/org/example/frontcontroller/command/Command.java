package org.example.frontcontroller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    // 각 요청별 처리 메서드들은 request, response 객체를 받아서 사용
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
