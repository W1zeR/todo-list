package com.w1zer.controllers.user.todo;

import com.w1zer.service.TODOService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/user/todo/new")
public class InsertController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(InsertController.class);
    private final TODOService todoService = new TODOService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/user/todo/new.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("Error while forwarding request with doGet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = (String) req.getSession().getAttribute("login");
        String comment = req.getParameter("comment");
        Date shouldBeDoneBefore = Date.valueOf(req.getParameter("shouldBeDoneBefore"));
        todoService.addTODO(login, comment, shouldBeDoneBefore);
        try {
            resp.sendRedirect("/user/todo");
        } catch (IOException e) {
            logger.error("Error while sending redirect with doPost", e);
        }
    }
}