package ru.kos.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kos.shop.domain.User;
import ru.kos.shop.reporitory.UserRepository;
import ru.kos.shop.util.PasswordEncrypter;

import java.util.List;

/**
 * Сервис для работы с пользователями <br/>
 * Created by Константин on 05.04.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncrypter passwordEncrypter;

    /**
     * Регистрация пользователя
     * @param userName имя
     * @param password пароль
     * @param rolesList список ролей {@link ru.kos.shop.security.Roles}
     * @return Созданный пользователь.
     */
    @Transactional
    public User registerUser(String userName, String password, List<String> rolesList) {
        User user = new User(userName, passwordEncrypter.encrypt(password), rolesList);
        userRepository.save(user);
        return user;
    }

    /**
     * Поиск пользователя
     * @param userName имя
     * @param pass пароль
     * @return Пользователь. Null если не найден.
     */
    public User findUser(String userName, String pass) {
        return userRepository.findByUserNameAndPassHash(userName, passwordEncrypter.encrypt(pass));
    }
}
