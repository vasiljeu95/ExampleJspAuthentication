package com.github.vasiljeu95.examplejspauthentication.servlets.servlet;

import com.github.vasiljeu95.examplejspauthentication.dao.ServiceDao;
import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
            final UserRole userRole = (UserRole) session.getAttribute("role");

            moveToMenu(req, resp, userRole);
        } else {
            req.getRequestDispatcher("/pages/authentication.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<ServiceDao> dao = (AtomicReference<ServiceDao>) req.getServletContext().getAttribute("dao");
        final HttpSession session = req.getSession();

        if (dao.get().userIsExist(login, password)) {
            final UserRole userRole = dao.get().getRoleByLoginPassword(login, password);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", userRole);

            moveToMenu(req, resp, userRole);
        } else {
            moveToMenu(req, resp, UserRole.UNKNOWN_ROLE);
        }
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse resp, final UserRole userRole) throws ServletException, IOException {
        if (userRole.equals(UserRole.ADMIN_ROLE) || userRole.equals(UserRole.USER_ROLE)) {
//            req.getRequestDispatcher("/pages/homePage.jsp").forward(req, resp);
            resp.sendRedirect(getServletContext().getContextPath()+"/pages/homePage.jsp");
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
