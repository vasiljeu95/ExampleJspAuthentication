package com.github.vasiljeu95.examplejspauthentication.dao.inMemoryStorage;

import com.github.vasiljeu95.examplejspauthentication.dao.RegisterStorage;
import com.github.vasiljeu95.examplejspauthentication.dao.UserServiceDao;
import com.github.vasiljeu95.examplejspauthentication.model.User;
import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

public class RegistrationInMemoryStorage implements RegisterStorage {
    public RegistrationInMemoryStorage() {
    }

    @Override
    public void save(String login, String password) {
        UserServiceDao.getInstance().addUser(new User(login, password, UserRole.USER_ROLE));
    }

    @Override
    public boolean find(String login) {
        return UserServiceDao.getInstance().userIsRegistered(login);
    }
}
