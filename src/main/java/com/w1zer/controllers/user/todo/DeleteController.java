package com.w1zer.controllers.user.todo;

import com.w1zer.service.TODOService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/todo/delete")
public class DeleteController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteController.class);
    private final TODOService todoService = new TODOService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("todoId"));
        String login = (String) req.getSession().getAttribute("login");
        if (todoService.canModify(login, id)) {
            todoService.deleteTODO(id);
        }
        try {
            resp.sendRedirect("/user/todo");
        } catch (IOException e) {
            logger.error("Error while sending redirect with doPost", e);
        }
    }
}