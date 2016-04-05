package ru.kos.shop.domain;

import ru.kos.shop.security.Roles;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Константин on 05.04.2016.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String userName;

    private String passHash;

    @ElementCollection
    private List<Roles> roles;

    public User() {
    }

    public User(String userName, String passHash, List<Roles> roles) {
        this.userName = userName;
        this.passHash = passHash;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassHash() {
        return passHash;
    }

    public List<Roles> getRoles() {
        return roles;
    }
}
