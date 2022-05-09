package com.github.vasiljeu95.examplejspauthentication.dao;

import com.github.vasiljeu95.examplejspauthentication.model.User;
import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserServiceDao {
    private static UserServiceDao instance;
    private List<User> userStore = new ArrayList<>();

    public static UserServiceDao getInstance() {
        if (instance == null) {
            instance = new UserServiceDao();
        }
        return instance;
    }

    public boolean addUser(final User user) {
        for (User u : userStore) {
            if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword())) {
                return false;
            }
        }
        return userStore.add(user);
    }

    public UserRole getUserRoleByLoginPassword(final String login, final String password) {
        UserRole userRole = UserRole.UNKNOWN_ROLE;

        for (User user : userStore) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                userRole = user.getUserRole();
            }
        }
        return userRole;
    }

    public boolean userIsExist(final String login, final String password) {
        boolean result = false;
        for (User user : userStore) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean userIsRegistered(final String login) {
        boolean result = false;
        for (User user : userStore) {
            if (user.getLogin().equals(login)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public User getUserById (int id) {
        User resultUser = new User();
        resultUser.setId(-1L);

        for (User user : userStore) {
            if (user.getId() == id) {
                resultUser = user;
            }
        }
        return resultUser;
    }

    public User getUserByLoginPassword(final String login, final String password) {
        User result = new User();
        result.setId(-1L);

        for (User user : userStore) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }
        return result;
    }
}
