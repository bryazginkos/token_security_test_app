package ru.kos.shop.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Пользователь <br/>
 * Created by Константин on 05.04.2016.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String userName;

    private String passHash;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User() {
    }

    public User(String userName, String passHash, List<String> roles) {
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

    public List<String> getRoles() {
        return roles;
    }
}
