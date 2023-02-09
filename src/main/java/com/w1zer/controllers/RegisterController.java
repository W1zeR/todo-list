package com.w1zer.controllers;

import com.w1zer.service.RegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RegisterController.class);
    private final RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("Error while forwarding request with doGet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        if (registerService.canRegister(login)) {
            String password = req.getParameter("password");
            registerService.register(login, password);
            req.setAttribute("msg", "Registration was successful");
        } else {
            req.setAttribute("error", "User with this login already exists");
        }
        try {
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("Error while forwarding request with doPost", e);
        }
    }
}
