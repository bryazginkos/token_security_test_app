package ru.kos.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kos.shop.domain.User;
import ru.kos.shop.reporitory.UserRepository;
import ru.kos.shop.security.Roles;
import ru.kos.shop.util.PasswordEncrypter;

import java.util.List;

/**
 * Created by Константин on 05.04.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncrypter passwordEncrypter;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User registerUser(String userName, String password, List<Roles> rolesList) {
        User user = new User(userName, passwordEncrypter.encrypt(password), rolesList);
        userRepository.save(user);
        return user;
    }

    public User findUser(String userName, String pass) {
        return userRepository.findByUserNameAndPassHash(userName, passwordEncrypter.encrypt(pass));
    }
}
