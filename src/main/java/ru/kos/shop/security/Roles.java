package ru.kos.shop.security;

/**
 * Created by Константин on 05.04.2016.
 */
public enum Roles {

    ROLE_ADMIN("ROLE_ADMIN");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
