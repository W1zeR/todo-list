package com.w1zer.controllers.user;

import com.w1zer.service.TODOService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/todo")
public class TODOController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(TODOController.class);
    private final TODOService todoService = new TODOService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String login = (String) req.getSession().getAttribute("login");
        req.setAttribute("todoList", todoService.getTODOList(login));
        try {
            req.getRequestDispatcher("/user/todo.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("Error while forwarding request with doGet", e);
        }
    }
}
