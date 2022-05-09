package com.github.vasiljeu95.examplejspauthentication.dao;

public interface RegisterStorage {
    void save(String login, String password);

    boolean find(String login);
}
