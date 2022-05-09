package com.github.vasiljeu95.examplejspauthentication.servlets.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/pages/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect(getServletContext().getContextPath()+"/index.jsp");
//        response.sendRedirect("index.jsp");
    }

}
