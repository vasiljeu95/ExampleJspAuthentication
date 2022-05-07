package com.github.vasiljeu95.examplejspauthentication.servlets.listener;

import com.github.vasiljeu95.examplejspauthentication.dao.ServiceDao;
import com.github.vasiljeu95.examplejspauthentication.model.User;
import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {

    private AtomicReference<ServiceDao> dao;

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        dao = new AtomicReference<>(new ServiceDao());

        dao.get().add(new User(1, "Vlad", "1234", UserRole.ADMIN_ROLE));
        dao.get().add(new User(2, "Stepan", "4321", UserRole.USER_ROLE));

        final ServletContext servletContext = contextEvent.getServletContext();
        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
