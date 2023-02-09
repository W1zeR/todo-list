package com.w1zer.controllers.user.todo;

import com.w1zer.model.TODO;
import com.w1zer.model.TODOBuilder;
import com.w1zer.model.TODOStatus;
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

@WebServlet("/user/todo/edit")
public class UpdateController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UpdateController.class);
    private final TODOService todoService = new TODOService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("todoId"));
        String login = (String) req.getSession().getAttribute("login");
        if (todoService.canModify(login, id)) {
            req.setAttribute("todo", todoService.getTODO(id));
            try {
                req.getRequestDispatcher("/user/todo/edit.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                logger.error("Error while forwarding request with doGet to edit.jsp", e);
            }
        } else {
            try {
                req.getRequestDispatcher("/error/404.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                logger.error("Error while forwarding request with doGet to 404.jsp", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("todoId"));
        String comment = req.getParameter("comment");
        Date shouldBeDoneBefore = Date.valueOf(req.getParameter("shouldBeDoneBefore"));
        TODOStatus status = TODOStatus.valueOf(req.getParameter("status"));
        TODO todo = TODOBuilder.newBuilder()
                .withId(id)
                .withComment(comment)
                .withShouldBeDoneBefore(shouldBeDoneBefore)
                .withStatus(status)
                .build();
        todoService.editTODO(todo);
        try {
            resp.sendRedirect("/user/todo");
        } catch (IOException e) {
            logger.error("Error while sending redirect with doPost", e);
        }
    }
}