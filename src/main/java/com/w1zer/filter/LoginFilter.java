package com.w1zer.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/*")
public class LoginFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) {
        HttpServletRequest httpServletReq = (HttpServletRequest) req;
        Object logged = httpServletReq.getSession().getAttribute("logged");
        if (logged != null && (boolean) logged) {
            try {
                fc.doFilter(req, resp);
            } catch (IOException | ServletException e) {
                logger.error("Error while filtering request when user isn't logged", e);
            }
        } else {
            HttpServletResponse httpServletResp = (HttpServletResponse) resp;
            try {
                httpServletResp.sendRedirect("/login");
            } catch (IOException e) {
                logger.error("Error while sending redirect with doFilter", e);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
