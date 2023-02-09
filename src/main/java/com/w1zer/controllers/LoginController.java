package com.w1zer.controllers;

import com.w1zer.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("Error while forwarding request with doGet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (loginService.canLogin(login, password)) {
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("logged", true);
            try {
                resp.sendRedirect("/user/todo");
            } catch (IOException e) {
                logger.error("Error while sending redirect with doPost", e);
            }
        } else {
            req.setAttribute("error", "Incorrect login or password");
            try {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                logger.error("Error while forwarding request with doPost", e);
            }
        }
    }
}
