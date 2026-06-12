package org.example.frontcontroller;

import org.example.frontcontroller.command.Command;
import org.example.frontcontroller.controller.HomeController;
import org.example.frontcontroller.controller.TodoController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "front-controllerServlet", value = "/")
public class FrontControllerServlet extends HttpServlet {

    // URL과 Command
    Map<String, Command> getMap;
    Map<String, Command> postMap;

    // 반복 사용되는 접두/접미사
    String prefix = "/WEB-INF/views/";
    String suffix = ".jsp";

    HomeController homeController = new HomeController();
    TodoController todoController = new TodoController();

    // GET /hello -> 실질적으로 동작할 메서드 객체로
    public void init() {
        getMap = new HashMap<>();
        postMap = new HashMap<>();

        // GET 요청 등록
//        getMap.put("/", ((request, response) -> homeController.getIndex(request, response)));

        getMap.put("/", homeController::getIndex);

        // GET 매핑
        getMap.put("/todo/list", todoController::getList);
        getMap.put("/todo/view", todoController::getView);
        getMap.put("/todo/create", todoController::getCreate);
        getMap.put("/todo/update", todoController::getUpdate);

        // POST 매핑
        postMap.put("/todo/create", todoController::postCreate);
        postMap.put("/todo/update", todoController::postUpdate);
        postMap.put("/todo/delete", todoController::postDelete);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Command command = getCommand(request);

        if (command != null) {
            execute(command, request, response);
        } else {
            String view = prefix + "404" + suffix;

            request.getRequestDispatcher(view).forward(request, response);
            System.out.println("일치하는 페이지가 없습니다.");
        }
    }

    // request 객체에서 명령어에 해당하는 url 찾아오기
    private String getCommandName(HttpServletRequest request) {

        // /아티팩트/hello
        String requestURI = request.getRequestURI();

        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);

        // requestURI에서 contextPath 제외
        String commandName = requestURI.substring(contextPath.length());
        System.out.println("contextPath = " + commandName);

        return commandName;
    }

    // Map 안에서 c
    private Command getCommand(HttpServletRequest request) {
        Command command;

        // "/"
        String commandName = getCommandName(request);

        if (request.getMethod().equalsIgnoreCase("GET")) {
            // get 요청일때
            command = getMap.get(commandName);
        } else {
            // get 요청이 아닐때
            command = postMap.get(commandName);
        }

        return command;
    }

    // 매개변수로 받은 command를 request, response를 담아 실행
    // command를 실행시켜 forward 해야할 jsp 파일 경로 전달.
    public void execute(Command command, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // "index"
        String viewName = command.execute(request, response);

        if (viewName.startsWith("redirect:")) {
            // redirect 로직
            response.sendRedirect(viewName.substring("redirect:".length()));
        } else {
            // forward 처리
//            System.out.println("viewName = " + viewName);
            String view = prefix + viewName + suffix;
//            request.getRequestDispatcher("/WEB-INF/views/index.jsp");
            request.getRequestDispatcher(view).forward(request, response);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
    }
}