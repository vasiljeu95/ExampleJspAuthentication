package com.github.vasiljeu95.examplejspauthentication.servlets.filter;

import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession session = httpServletRequest.getSession();
        UserRole userRole = (UserRole) session.getAttribute("role");

        if (userRole.equals(UserRole.ADMIN_ROLE) || userRole.equals(UserRole.USER_ROLE)) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/pages/authentication.jsp");
        }
    }

    public void destroy() {
    }
}
