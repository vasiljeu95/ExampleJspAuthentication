package com.github.vasiljeu95.examplejspauthentication.servlets.servlet;

import com.github.vasiljeu95.examplejspauthentication.dao.UserServiceDao;
import com.github.vasiljeu95.examplejspauthentication.dao.inMemoryStorage.RegistrationInMemoryStorage;
import com.github.vasiljeu95.examplejspauthentication.dao.sqlStorage.RegistrationInSqlStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    private AtomicReference<UserServiceDao> dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationInMemoryStorage registerInMemoryStorage = new RegistrationInMemoryStorage();
        RegistrationInSqlStorage registrationInSqlStorage = new RegistrationInSqlStorage();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
//        dao = new AtomicReference<>(new UserServiceDao());

        if (registerInMemoryStorage.find(login) || registrationInSqlStorage.find(login)) {
            request.setAttribute("Message", "User with such login exists");
            response.sendRedirect(getServletContext().getContextPath()+"/pages/registration.jsp");
        } else {
            registerInMemoryStorage.save(login, password);
            registrationInSqlStorage.save(login, password);
            //add Logg
//            logger.info("New user has been registered");
//            response.sendRedirect(getServletContext().getContextPath()+"/pages/authentication.jsp");
            request.getRequestDispatcher("/pages/authentication.jsp").forward(request, response);
        }
    }
}
