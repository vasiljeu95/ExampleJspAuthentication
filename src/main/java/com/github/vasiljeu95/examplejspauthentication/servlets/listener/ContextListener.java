package com.github.vasiljeu95.examplejspauthentication.servlets.listener;

import com.github.vasiljeu95.examplejspauthentication.dao.UserServiceDao;
import com.github.vasiljeu95.examplejspauthentication.model.User;
import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {

    private AtomicReference<UserServiceDao> dao;

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        dao = new AtomicReference<>(new UserServiceDao());

        dao.get().addUser(new User("Vlad", "1234", UserRole.ADMIN_ROLE));
        dao.get().addUser(new User("Stepan", "4321", UserRole.USER_ROLE));

        final ServletContext servletContext = contextEvent.getServletContext();
        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
