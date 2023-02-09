package com.w1zer.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("*.jsp")
public class JspFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(JspFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) {
        try {
            req.getRequestDispatcher("/error/404.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("Error while filtering .jsp", e);
        }
    }

    @Override
    public void destroy() {
    }
}
