package com.github.vasiljeu95.examplejspauthentication.servlets.servlet;

import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/startPageServlet")
public class StartPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final String login = req.getParameter("login");
//        final String password = req.getParameter("password");

//        @SuppressWarnings("unchecked")
//        final AtomicReference<ServiceDao> dao = (AtomicReference<ServiceDao>) req.getServletContext().getAttribute("dao");
        final HttpSession session = req.getSession();

        //Logged user.
        if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
            final UserRole userRole = (UserRole) session.getAttribute("role");

            if (userRole.equals(UserRole.ADMIN_ROLE)) {
                req.getRequestDispatcher("/pages/homePage.jsp").forward(req, resp);
            } else if (userRole.equals(UserRole.USER_ROLE)) {
                req.getRequestDispatcher("/pages/homePage.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
