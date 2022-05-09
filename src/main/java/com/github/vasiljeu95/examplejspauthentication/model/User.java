package com.github.vasiljeu95.examplejspauthentication.model;

public class User {
    private static Long counter = 0L;
    private Long id;
    private String login;
    private String password;
    private UserRole userRole;

    public User() {
    }

    public User(String login, String password, UserRole userRole) {
        counter++;
        this.id = counter;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
