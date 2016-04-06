package ru.kos.shop.dto;

import java.io.Serializable;

/**
 * Created by Константин on 06.04.2016.
 */
public class AuthParams implements Serializable {

    private String login;

    private String password;

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
}
