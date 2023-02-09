package com.w1zer.controllers.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/logout")
public class LogoutController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LogoutController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("login", null);
        req.getSession().setAttribute("logged", false);
        try {
            resp.sendRedirect("/login");
        } catch (IOException e) {
            logger.error("Error while sending redirect with doGet", e);
        }
    }
}